package com.voidK.gitview.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.voidK.gitview.models.GitQueryRepoModel.GitQueryRepoItem;
import com.voidK.gitview.models.GitQueryRepoModel.Owner;
import com.voidK.gitview.utils.Constants;

@Database(entities = {GitQueryRepoItem.class}, version = 1, exportSchema = false)
@TypeConverters(Owner.TypeConverterOwner.class)
public abstract class GitQueryDatabase extends RoomDatabase {

    public static GitQueryDatabase INSTANCE;
    public abstract GitQueryDao GitQueryDao();

    public static GitQueryDatabase getDatabase(Context context){
        if(INSTANCE == null) {
            INSTANCE =  Room.databaseBuilder(context,
                            GitQueryDatabase.class, Constants.GIT_REPO_DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;

    }
}
