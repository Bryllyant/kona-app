package com.bryllyant.kona.util;

import com.bryllyant.kona.app.model.SocialHandle;
import com.bryllyant.kona.app.model.SocialHandle.Network;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SocialHandleHandler extends BaseTypeHandler<List<SocialHandle>> {
    private static final Logger logger = LoggerFactory.getLogger(SocialHandleHandler.class);

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
                                    List parameter, JdbcType jdbcType) throws SQLException {
        String json = null;

        if (parameter != null) {
            json = KJsonUtil.toJson(parameter);
        }

        ps.setString(i, json);
    }

    @Override
    public List getNullableResult(ResultSet rs, String columnName)
            throws SQLException {

        String json = rs.getString(columnName);
        return valueOf(json);
    }

    @Override
    public List getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {

        String json = rs.getString(columnIndex);
        return valueOf(json);
    }

    @Override
    public List getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {

        String json = cs.getString(columnIndex);
        return valueOf(json);
    }

    // FIXME: make this more efficient
    private List valueOf(String value) {
        if (value == null) return null;

        List<Map<String,Object>> items = KJsonUtil.toList(value);

        logger.debug("valueOf:  items:  {}", KJsonUtil.toJson(items));

        List<SocialHandle> handles = new ArrayList<>();

        for (Map<String,Object> item : items) {
            logger.debug("valueOf:  item:  {}", KJsonUtil.toJson(item));

            SocialHandle handle = new SocialHandle();

            for (String key : item.keySet()) {
                Object _value = item.get(key);

                if (_value == null) continue;

                switch (key) {
                    case "network":
                        Network network = Network.valueOf(_value.toString());
                        handle.setNetwork(network);
                        break;

                    case "url":
                        handle.setUrl(_value.toString());
                        break;

                    case "handle":
                        handle.setHandle(_value.toString());
                        break;
                }
            }

            handles.add(handle);
        }

        return handles;
    }
}
