package com.example.firstcode.sixth_chapter.db.litepal;

import org.litepal.crud.LitePalSupport;

/**
 * 说明：Category 表
 * <p>
 * date: 2020-02-21 10:15
 *
 * @author syd
 * @version 1.0
 */
public class Category  extends LitePalSupport {
    private String categoryName;
    private int categoryCode;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }
}
