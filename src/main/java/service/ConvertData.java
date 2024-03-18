package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.Collection;
import java.util.List;

public class ConvertData implements IConvertData {

    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public <T> T getData(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> getListData(String json, Class<T> classe) {
        try {
            CollectionType list = mapper.getTypeFactory()
                    .constructCollectionType(List.class, classe);
            return mapper.readValue(json, list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
