package com.assignment.nytimes.screens.articledetail;

import com.assignment.nytimes.screens.articlelist.model.ArticleDataResults;

public interface ClickHandler {
    void onArticleClicked(ArticleDataResults article);
}