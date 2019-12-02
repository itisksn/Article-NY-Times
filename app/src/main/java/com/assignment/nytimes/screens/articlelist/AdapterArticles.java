package com.assignment.nytimes.screens.articlelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.nytimes.R;
import com.assignment.nytimes.databinding.ListAdapterBinding;
import com.assignment.nytimes.screens.articledetail.ClickHandler;
import com.assignment.nytimes.screens.articlelist.model.ArticleDataResults;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class AdapterArticles extends RecyclerView.Adapter<AdapterArticles.ViewHolderDataClass> {

    private Context context;
    private ArrayList<ArticleDataResults> articleList;

    public AdapterArticles(Context context, ArrayList<ArticleDataResults> articleList) {
        this.context = context;
        this.articleList = articleList;

    }

    @NonNull
    @Override
    public ViewHolderDataClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListAdapterBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.list_adapter, parent, false);

        return new ViewHolderDataClass(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDataClass holder, int position) {
        holder.binding.setArticle(articleList.get(position));

        try {
            if (articleList.get(position).getMedia() != null
                    && articleList.get(position).getMedia().get(0) != null
                    && articleList.get(position).getMedia().get(0).getMedia_metadata() != null) {
                Glide
                        .with(context)
                        .load(articleList.get(position).getMedia().get(0).getMedia_metadata().get(0).getUrl())
                        .centerCrop()
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(holder.binding.imgArticle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.binding.setClickListener(new ClickHandler() {
            @Override
            public void onArticleClicked(ArticleDataResults article) {
                Navigation.findNavController(holder.binding.getRoot()).navigate(FragmentArticleListDirections.actionFragmentNavigateArticleDetail(article));
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class ViewHolderDataClass extends RecyclerView.ViewHolder {
        private ListAdapterBinding binding;

        public ViewHolderDataClass(@NonNull ListAdapterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


    }
 /*   public void updateArticles( ArrayList<ArticleDataResults> articleList)
    {
        if(this.articleList==null) {
            this.articleList=new ArrayList<>();
        }
        this.articleList.addAll(articleList);

        notifyDataSetChanged();
    }*/

}
