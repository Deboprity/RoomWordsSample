package android.example.com.roomwordssample;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "word_table", indices = @Index(value = {"word"}, unique = true))
public class Word implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;
    public Word(@NonNull String word) {this.mWord = word;}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getWord(){return this.mWord;}
    public void setWord(@NonNull String mWord) { this.mWord = mWord; }
}
