package com.assignment.nytimes.screens.articledetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.assignment.nytimes.BaseFragment;
import com.assignment.nytimes.R;
import com.assignment.nytimes.databinding.FragmentArticleDetailBinding;
import com.assignment.nytimes.screens.articlelist.model.ArticleDataResults;
import com.bumptech.glide.Glide;

public class FragmentArticleDetail extends BaseFragment {

    private FragmentArticleDetailBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_article_detail, container, false);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArticleDataResults articleDataResults = FragmentArticleDetailArgs.fromBundle(getArguments()).getArticle();
        binding.setArticle(articleDataResults);
        try {
            if (articleDataResults.getMedia() != null && articleDataResults.getMedia().get(0).getMedia_metadata() != null
                    && articleDataResults.getMedia().get(0).getMedia_metadata().get(1).getUrl() != null) {
                Glide
                        .with(getActivity())
                        .load(articleDataResults.getMedia().get(0).getMedia_metadata().get(1).getUrl())
                        .centerCrop()
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(binding.imageBackground);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
