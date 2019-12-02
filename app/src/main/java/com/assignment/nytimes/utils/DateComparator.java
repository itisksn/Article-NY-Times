package com.assignment.nytimes.utils;

import com.assignment.nytimes.screens.articlelist.model.ArticleDataResults;

import java.util.Comparator;

public class DateComparator implements Comparator<ArticleDataResults> {
    @Override
    public int compare(ArticleDataResults article1, ArticleDataResults article2) {
        return article2.getPublished_date().compareTo(article1.getPublished_date());
    }
}