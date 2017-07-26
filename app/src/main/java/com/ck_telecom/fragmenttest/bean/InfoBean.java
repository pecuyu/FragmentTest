package com.ck_telecom.fragmenttest.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfoBean {

    public static final List<InfoItem> ITEMS = new ArrayList<InfoItem>();

    public static final Map<String, InfoItem> ITEM_MAP = new HashMap<String, InfoItem>();

    private static final int COUNT = 25;

    static {
        // Add some  items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createInfoItem(i));
        }
    }

    private static void addItem(InfoItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static InfoItem createInfoItem(int position) {
        return new InfoItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }


    public static class InfoItem {
        public final String id;
        public final String content;
        public final String details;

        public InfoItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
