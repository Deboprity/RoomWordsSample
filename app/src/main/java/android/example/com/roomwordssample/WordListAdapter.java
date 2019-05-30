package android.example.com.roomwordssample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LayoutInflater mInflater;
    private List<Word> mWords; // Cached copy of words
    private OnItemClicked onClick;

    WordListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @NotNull
    @Override
    public WordViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NotNull WordViewHolder holder, int position) {
        if (mWords != null) {
            final Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
            holder.wordItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClick.onItemClick(current);
                }
            });
            holder.wordItemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onClick.onItemLongClick(current);
                    return false;
                }
            });
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText(R.string.err_no_word);
        }
    }

    void setWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }

    void setOnClick(OnItemClicked onClick) {
        this.onClick = onClick;
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }

    interface OnItemClicked {
        void onItemClick(Word selectedWord);
        void onItemLongClick(Word selectedWord);
    }
}
