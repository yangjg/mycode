package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangjingan on 17-3-29.
 */
public class ParseFile {

    private List<DataItem> listItem;


    private ParseFile() {
        listItem = new ArrayList<>();
    }

    public static ParseFile getInStance() {
        return ParseFileHolder.sInstance;
    }

    public void processFile(String fileName, String newFileName, String startFlag, String endFlag, String splite) {
        listItem.clear();
        FileInputStream inputStream = null;
        InputStreamReader read = null;
        BufferedReader reader = null;
        try {
            ItemStack stack = new ItemStack(startFlag, endFlag);
            inputStream = new FileInputStream(fileName);
            read = new InputStreamReader(inputStream);
            reader = new BufferedReader(read);
            String text = reader.readLine();
            while (null != text) {
                DataItem item = stack.pushStack(parseLine(text, splite));
                if (null != item) {
                    listItem.add(item);
                }
                text = reader.readLine();
            }
            stack.clear();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (null != read) {
                    read.close();
                }
                if (null != reader) {
                    reader.close();
                }
            } catch (Exception e) {

            }
        }
        removeRepeatAndWriteFile(listItem, newFileName);
        return;
    }

    private void removeRepeatAndWriteFile(List<DataItem> items, String saveFileName) {
        for (int i = 0; i < items.size() - 1; i++) {
            if (items.get(i).getShouldRemoveRepeat()) {
                for (int j = i + 1; j < items.size(); j++) {
                    if (items.get(j).getShouldRemoveRepeat()) {
                        if (items.get(i).getText().equals(items.get(j).getText())) {
                            items.remove(j);
                        }
                    }
                }
            }
        }
        File file = new File(saveFileName);
        FileOutputStream os = null;
        OutputStreamWriter osw = null;
        BufferedWriter writer = null;
        try {
            if (!file.exists()) file.createNewFile();
            os = new FileOutputStream(file);
            osw = new OutputStreamWriter(os);
            writer = new BufferedWriter(osw);
            for (int i = 0; i < items.size(); i++) {
                items.get(i).setId(i);
                writer.write(items.get(i).toString());
            }
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (osw != null) {
                    osw.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception e) {

            }
        }

    }

    private LineDataItem parseLine(String text, String split) {
        String res = text;
        String res2 =null;
        if (null != text && "" != text) {
            int index = text.lastIndexOf(split);
            if (index >= 0) {
                res2= text.substring(0,index+split.length());
                res = text.substring(index + split.length());
            }
            res = res.trim();
        }
        LineDataItem item = new LineDataItem(res2,res);
        return item;
    }

    private static class ParseFileHolder {
        private final static ParseFile sInstance = new ParseFile();
    }

}
