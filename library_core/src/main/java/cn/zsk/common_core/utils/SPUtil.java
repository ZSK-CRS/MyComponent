package cn.zsk.common_core.utils;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.collection.SimpleArrayMap;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import butterknife.internal.Utils;
import cn.zsk.common_core.base.app.AppConfig;

/**
 * Author : ZSK
 * Date : 2020/3/7
 * Description :  SharedPreferences文件处理
 */

public class SPUtil {

    private static SimpleArrayMap<String, SPUtil> SP_UTILS_MAP = new SimpleArrayMap();
    private SharedPreferences sp;

    public static SPUtil getInstance() {
        return getInstance("");
    }

    public static SPUtil getInstance(String spName) {
        if (isSpace(spName)) {
            spName = "appConfig";
        }

        SPUtil spUtils = (SPUtil)SP_UTILS_MAP.get(spName);
        if (spUtils == null) {
            spUtils = new SPUtil(spName);
            SP_UTILS_MAP.put(spName, spUtils);
        }

        return spUtils;
    }

    private SPUtil(String spName) {
        this.sp = AppConfig.INSTANCE.getApplication().getSharedPreferences(spName, 0);
    }

    public void put(@NonNull String key, @NonNull String value) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (value == null) {
            throw new NullPointerException("Argument 'value' of type String (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            this.put(key, value, true);
        }
    }

    public void put(@NonNull String key, @NonNull String value, boolean isCommit) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (value == null) {
            throw new NullPointerException("Argument 'value' of type String (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            if (isCommit) {
                this.sp.edit().putString(key, value).commit();
            } else {
                this.sp.edit().putString(key, value).apply();
            }

        }
    }

    public String getString(@NonNull String key) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            return this.getString(key, "");
        }
    }

    public String getString(@NonNull String key, @NonNull String defaultValue) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (defaultValue == null) {
            throw new NullPointerException("Argument 'defaultValue' of type String (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            return this.sp.getString(key, defaultValue);
        }
    }

    public void put(@NonNull String key, int value) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            this.put(key, value, true);
        }
    }

    public void put(@NonNull String key, int value, boolean isCommit) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            if (isCommit) {
                this.sp.edit().putInt(key, value).commit();
            } else {
                this.sp.edit().putInt(key, value).apply();
            }

        }
    }

    public int getInt(@NonNull String key) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            return this.getInt(key, -1);
        }
    }

    public int getInt(@NonNull String key, int defaultValue) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            return this.sp.getInt(key, defaultValue);
        }
    }

    public void put(@NonNull String key, long value) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            this.put(key, value, true);
        }
    }

    public void put(@NonNull String key, long value, boolean isCommit) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            if (isCommit) {
                this.sp.edit().putLong(key, value).commit();
            } else {
                this.sp.edit().putLong(key, value).apply();
            }

        }
    }

    public long getLong(@NonNull String key) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            return this.getLong(key, -1L);
        }
    }

    public long getLong(@NonNull String key, long defaultValue) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            return this.sp.getLong(key, defaultValue);
        }
    }

    public void put(@NonNull String key, float value) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            this.put(key, value, true);
        }
    }

    public void put(@NonNull String key, float value, boolean isCommit) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            if (isCommit) {
                this.sp.edit().putFloat(key, value).commit();
            } else {
                this.sp.edit().putFloat(key, value).apply();
            }

        }
    }

    public float getFloat(@NonNull String key) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            return this.getFloat(key, -1.0F);
        }
    }

    public float getFloat(@NonNull String key, float defaultValue) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            return this.sp.getFloat(key, defaultValue);
        }
    }

    public void put(@NonNull String key, boolean value) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            this.put(key, value, true);
        }
    }

    public void put(@NonNull String key, boolean value, boolean isCommit) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            if (isCommit) {
                this.sp.edit().putBoolean(key, value).commit();
            } else {
                this.sp.edit().putBoolean(key, value).apply();
            }

        }
    }

    public boolean getBoolean(@NonNull String key) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            return this.getBoolean(key, false);
        }
    }

    public boolean getBoolean(@NonNull String key, boolean defaultValue) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            return this.sp.getBoolean(key, defaultValue);
        }
    }

    public void put(@NonNull String key, @NonNull Set<String> values) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (values == null) {
            throw new NullPointerException("Argument 'values' of type Set<String> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            this.put(key, values, true);
        }
    }

    public void put(@NonNull String key, @NonNull Set<String> values, boolean isCommit) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (values == null) {
            throw new NullPointerException("Argument 'values' of type Set<String> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            if (isCommit) {
                this.sp.edit().putStringSet(key, values).commit();
            } else {
                this.sp.edit().putStringSet(key, values).apply();
            }

        }
    }

    public Set<String> getStringSet(@NonNull String key) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            return this.getStringSet(key, Collections.emptySet());
        }
    }

    public Set<String> getStringSet(@NonNull String key, @NonNull Set<String> defaultValue) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (defaultValue == null) {
            throw new NullPointerException("Argument 'defaultValue' of type Set<String> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            return this.sp.getStringSet(key, defaultValue);
        }
    }

    public Map<String, ?> getAll() {
        return this.sp.getAll();
    }

    public boolean contains(@NonNull String key) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            return this.sp.contains(key);
        }
    }

    public void remove(@NonNull String key) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            this.remove(key, false);
        }
    }

    public void remove(@NonNull String key, boolean isCommit) {
        if (key == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            if (isCommit) {
                this.sp.edit().remove(key).commit();
            } else {
                this.sp.edit().remove(key).apply();
            }

        }
    }

    public void clear() {
        this.clear(false);
    }

    public void clear(boolean isCommit) {
        if (isCommit) {
            this.sp.edit().clear().commit();
        } else {
            this.sp.edit().clear().apply();
        }

    }

    private static boolean isSpace(String s) {
        if (s == null) {
            return true;
        } else {
            int i = 0;

            for(int len = s.length(); i < len; ++i) {
                if (!Character.isWhitespace(s.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }
}
