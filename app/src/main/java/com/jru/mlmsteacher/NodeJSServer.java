package com.jru.mlmsteacher;

import android.content.Context;
import android.content.res.AssetManager;

import com.jru.mlmsteacher.data.EZSharedPreferences;
import com.jru.mlmsteacher.util.Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class NodeJSServer {


    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("node");
    }

    private Context ctx;

    public NodeJSServer(Context ctx) {
        this.ctx = ctx;
    }

    private static boolean _startedNodeAlready = false;

    private final static String NODE_JS_PROJECT = "mobile-lms";
    private final static String APP = "index.js";

    private static String getNodeDirectory(Context ctx) {
        return ctx.getFilesDir().getAbsolutePath() + "/" + NODE_JS_PROJECT;
    }

    public void run() {
        if (!_startedNodeAlready) {
            _startedNodeAlready = true;
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            String nodeDir = getNodeDirectory(ctx);
                            if (Util.wasAPKUpdated(ctx)) {
                                File nodeDirReference = new File(nodeDir);
                                if (nodeDirReference.exists())
                                    deleteFolderRecursively(nodeDirReference);

                                copyAssetFolder(ctx.getAssets(), NODE_JS_PROJECT, nodeDir);
                                EZSharedPreferences.setAPKLastUpdate(ctx,
                                        Util.getAPKLastUpdate(ctx));

                            }

                            startNodeWithArguments(new String[]{"node",
                                    getNodeDirectory(ctx) + "/" + APP
                            });
                        }
                    }
            ).start();
        } else {
            // TODO: Throw some kind of exception
        }
    }

    public native Integer startNodeWithArguments(String[] arguments);

    private static boolean deleteFolderRecursively(File file) {
        try {
            boolean res = true;
            for (File childFile : file.listFiles()) {
                if (childFile.isDirectory()) {
                    res &= deleteFolderRecursively(childFile);
                } else {
                    res &= childFile.delete();
                }
            }
            res &= file.delete();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean copyAssetFolder(AssetManager assetManager, String fromAssetPath, String toPath) {
        try {
            String[] files = assetManager.list(fromAssetPath);
            boolean res = true;

            if (files.length == 0) {
                //If it's a file, it won't have any assets "inside" it.
                res = copyAsset(assetManager,
                        fromAssetPath,
                        toPath);
            } else {
                new File(toPath).mkdirs();
                for (String file : files)
                    res &= copyAssetFolder(assetManager,
                            fromAssetPath + "/" + file,
                            toPath + "/" + file);
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean copyAsset(AssetManager assetManager, String fromAssetPath, String toPath) {
        InputStream in;
        OutputStream out;
        try {
            in = assetManager.open(fromAssetPath);
            new File(toPath).createNewFile();
            out = new FileOutputStream(toPath);
            copyFile(in, out);
            in.close();
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

}
