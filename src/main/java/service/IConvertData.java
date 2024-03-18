package service;

import java.util.List;

public interface IConvertData {
    <T> T getData(String json, Class<T> classe);

    <T> List<T> getListData(String json, Class<T> classe);
}
