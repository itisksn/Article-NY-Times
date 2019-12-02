package com.assignment.nytimes.screens.articlelist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.nytimes.BaseFragment;
import com.assignment.nytimes.R;
import com.assignment.nytimes.databinding.FragmentArticleListBinding;
import com.assignment.nytimes.network.Resource;
import com.assignment.nytimes.screens.articlelist.model.ArticleData;


public class FragmentArticleList extends BaseFragment {

    private FragmentArticleListBinding binding;
    private AdapterArticles adapterArticles;
    private boolean isScrolled = false;
    private int periodValue = ArticlePeriod.TODAY.getPeriod();
    private ArticleListViewModel articleListViewModel;

    public FragmentArticleList() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_article_list, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inIt();

    }

    void inIt() {
        articleListViewModel = ViewModelProviders.of(getActivity()).get(ArticleListViewModel.class);
        subscribeViewModel();
        binding.recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolled = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int currentItem = binding.recycler.getLayoutManager().getChildCount();
                int totalItem = binding.recycler.getLayoutManager().getItemCount();
                LinearLayoutManager llm = (LinearLayoutManager) binding.recycler.getLayoutManager();
                int scrolledItem = llm.findFirstVisibleItemPosition();
                if (isScrolled && (currentItem + scrolledItem == totalItem)) {
                    isScrolled = false;
                    if (periodValue < ArticlePeriod.MONTHLY.getPeriod()) {
                        if (periodValue == ArticlePeriod.TODAY.getPeriod()) {
                            periodValue = ArticlePeriod.WEEKLY.getPeriod();
                        } else if (periodValue == ArticlePeriod.WEEKLY.getPeriod()) {
                            periodValue = ArticlePeriod.MONTHLY.getPeriod();
                        }
                        binding.progressBar.setVisibility(View.VISIBLE);
                        subscribeViewModel();
                    }
                }
            }
        });

    }


    private void subscribeViewModel() {
        articleListViewModel.getLiveDataProperty(periodValue).observe(getActivity(), new Observer<Resource<ArticleData>>() {
            @Override
            public void onChanged(@Nullable Resource<ArticleData> resource) {

                if (resource == null) {
                    return;
                }
                switch (resource.status) {
                    case LOADING:
                        binding.progressBar.setVisibility(View.GONE);
                        showProgressDialog(getActivity());
                        break;
                    case SUCCESS:
                        dismissProgressDialog();
                        if (resource.data != null) {
                            int selectedItem = 0;
                            if (adapterArticles != null) {
                                selectedItem = adapterArticles.getItemCount();

                            }
                            adapterArticles = new AdapterArticles(getActivity(), resource.data.getResults());
                            binding.recycler.setAdapter(adapterArticles);
                             binding.recycler.smoothScrollToPosition(selectedItem);


                        }
                        break;
                    case ERROR:
                        binding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), " " + getString(R.string.server_error), Toast.LENGTH_SHORT).show();
                        dismissProgressDialog();
                        break;
                }
            }
        });
    }

}
