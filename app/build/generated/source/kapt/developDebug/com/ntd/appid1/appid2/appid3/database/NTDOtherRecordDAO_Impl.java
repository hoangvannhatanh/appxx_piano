package com.ntd.appid1.appid2.appid3.database;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class NTDOtherRecordDAO_Impl implements NTDOtherRecordDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<NTDOtherRecord> __insertionAdapterOfNTDOtherRecord;

  private final EntityDeletionOrUpdateAdapter<NTDOtherRecord> __deletionAdapterOfNTDOtherRecord;

  private final EntityDeletionOrUpdateAdapter<NTDOtherRecord> __updateAdapterOfNTDOtherRecord;

  public NTDOtherRecordDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNTDOtherRecord = new EntityInsertionAdapter<NTDOtherRecord>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `recordEntity` (`id`,`file_name`,`file_path`,`duration_time`,`created_at`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NTDOtherRecord value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getFileName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getFileName());
        }
        if (value.getFilePath() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getFilePath());
        }
        stmt.bindLong(4, value.getDurationTime());
        stmt.bindLong(5, value.getCreatedAt());
      }
    };
    this.__deletionAdapterOfNTDOtherRecord = new EntityDeletionOrUpdateAdapter<NTDOtherRecord>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `recordEntity` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NTDOtherRecord value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfNTDOtherRecord = new EntityDeletionOrUpdateAdapter<NTDOtherRecord>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `recordEntity` SET `id` = ?,`file_name` = ?,`file_path` = ?,`duration_time` = ?,`created_at` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NTDOtherRecord value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getFileName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getFileName());
        }
        if (value.getFilePath() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getFilePath());
        }
        stmt.bindLong(4, value.getDurationTime());
        stmt.bindLong(5, value.getCreatedAt());
        if (value.getId() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, value.getId());
        }
      }
    };
  }

  @Override
  public void insertRecord(final NTDOtherRecord record) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfNTDOtherRecord.insert(record);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteRecord(final NTDOtherRecord record) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfNTDOtherRecord.handle(record);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateRecord(final NTDOtherRecord record) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfNTDOtherRecord.handle(record);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<NTDOtherRecord> getAllRecord() {
    final String _sql = "SELECT * FROM recordEntity ORDER BY id desc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfFileName = CursorUtil.getColumnIndexOrThrow(_cursor, "file_name");
      final int _cursorIndexOfFilePath = CursorUtil.getColumnIndexOrThrow(_cursor, "file_path");
      final int _cursorIndexOfDurationTime = CursorUtil.getColumnIndexOrThrow(_cursor, "duration_time");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final List<NTDOtherRecord> _result = new ArrayList<NTDOtherRecord>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final NTDOtherRecord _item;
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        final String _tmpFileName;
        if (_cursor.isNull(_cursorIndexOfFileName)) {
          _tmpFileName = null;
        } else {
          _tmpFileName = _cursor.getString(_cursorIndexOfFileName);
        }
        final String _tmpFilePath;
        if (_cursor.isNull(_cursorIndexOfFilePath)) {
          _tmpFilePath = null;
        } else {
          _tmpFilePath = _cursor.getString(_cursorIndexOfFilePath);
        }
        final long _tmpDurationTime;
        _tmpDurationTime = _cursor.getLong(_cursorIndexOfDurationTime);
        final long _tmpCreatedAt;
        _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
        _item = new NTDOtherRecord(_tmpId,_tmpFileName,_tmpFilePath,_tmpDurationTime,_tmpCreatedAt);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public NTDOtherRecord getRecord(final int id) {
    final String _sql = "SELECT * FROM recordEntity WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfFileName = CursorUtil.getColumnIndexOrThrow(_cursor, "file_name");
      final int _cursorIndexOfFilePath = CursorUtil.getColumnIndexOrThrow(_cursor, "file_path");
      final int _cursorIndexOfDurationTime = CursorUtil.getColumnIndexOrThrow(_cursor, "duration_time");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final NTDOtherRecord _result;
      if(_cursor.moveToFirst()) {
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        final String _tmpFileName;
        if (_cursor.isNull(_cursorIndexOfFileName)) {
          _tmpFileName = null;
        } else {
          _tmpFileName = _cursor.getString(_cursorIndexOfFileName);
        }
        final String _tmpFilePath;
        if (_cursor.isNull(_cursorIndexOfFilePath)) {
          _tmpFilePath = null;
        } else {
          _tmpFilePath = _cursor.getString(_cursorIndexOfFilePath);
        }
        final long _tmpDurationTime;
        _tmpDurationTime = _cursor.getLong(_cursorIndexOfDurationTime);
        final long _tmpCreatedAt;
        _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
        _result = new NTDOtherRecord(_tmpId,_tmpFileName,_tmpFilePath,_tmpDurationTime,_tmpCreatedAt);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
