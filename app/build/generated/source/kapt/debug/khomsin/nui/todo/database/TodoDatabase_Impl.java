package khomsin.nui.todo.database;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import khomsin.nui.todo.model.RegisterModel;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TodoDatabase_Impl implements TodoDatabase {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RegisterModel.Response.User> __insertionAdapterOfUser;

  private final EntityDeletionOrUpdateAdapter<RegisterModel.Response.User> __deletionAdapterOfUser;

  public TodoDatabase_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUser = new EntityInsertionAdapter<RegisterModel.Response.User>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `tbUser` (`generateId`,`age`,`createdAt`,`email`,`id`,`name`,`updatedAt`,`v`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RegisterModel.Response.User value) {
        stmt.bindLong(1, value.getGenerateId());
        if (value.getAge() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getAge());
        }
        if (value.getCreatedAt() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCreatedAt());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getEmail());
        }
        if (value.getId() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getId());
        }
        if (value.getName() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getName());
        }
        if (value.getUpdatedAt() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getUpdatedAt());
        }
        if (value.getV() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, value.getV());
        }
      }
    };
    this.__deletionAdapterOfUser = new EntityDeletionOrUpdateAdapter<RegisterModel.Response.User>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `tbUser` WHERE `generateId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RegisterModel.Response.User value) {
        stmt.bindLong(1, value.getGenerateId());
      }
    };
  }

  @Override
  public Object addUser(final RegisterModel.Response.User data,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUser.insert(data);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteUser(final RegisterModel.Response.User data,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfUser.handle(data);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public LiveData<List<RegisterModel.Response.User>> getUser() {
    final String _sql = "SELECT * FROM tbUser";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"tbUser"}, false, new Callable<List<RegisterModel.Response.User>>() {
      @Override
      public List<RegisterModel.Response.User> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfGenerateId = CursorUtil.getColumnIndexOrThrow(_cursor, "generateId");
          final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "age");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfV = CursorUtil.getColumnIndexOrThrow(_cursor, "v");
          final List<RegisterModel.Response.User> _result = new ArrayList<RegisterModel.Response.User>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final RegisterModel.Response.User _item;
            final int _tmpGenerateId;
            _tmpGenerateId = _cursor.getInt(_cursorIndexOfGenerateId);
            final Integer _tmpAge;
            if (_cursor.isNull(_cursorIndexOfAge)) {
              _tmpAge = null;
            } else {
              _tmpAge = _cursor.getInt(_cursorIndexOfAge);
            }
            final String _tmpCreatedAt;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmpCreatedAt = null;
            } else {
              _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpUpdatedAt;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmpUpdatedAt = null;
            } else {
              _tmpUpdatedAt = _cursor.getString(_cursorIndexOfUpdatedAt);
            }
            final Integer _tmpV;
            if (_cursor.isNull(_cursorIndexOfV)) {
              _tmpV = null;
            } else {
              _tmpV = _cursor.getInt(_cursorIndexOfV);
            }
            _item = new RegisterModel.Response.User(_tmpGenerateId,_tmpAge,_tmpCreatedAt,_tmpEmail,_tmpId,_tmpName,_tmpUpdatedAt,_tmpV);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
