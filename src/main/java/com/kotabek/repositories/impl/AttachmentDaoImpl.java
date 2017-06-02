package com.kotabek.repositories.impl;

import com.kotabek.repositories.AttachmentDao;
import com.kotabek.to.AttachmentFilterTO;
import com.kotabek.to.AttachmentTO;
import com.kotabek.utils.DG;
import com.kotabek.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kotabek on 6/1/17.
 */
@Repository
public class AttachmentDaoImpl implements AttachmentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(AttachmentTO to) {
        String sql = "INSERT INTO attachment(name, mime, size, path, created) VALUES(?,?,?,?,?)";
        return jdbcTemplate.update(sql, DG.getString(to.getName()),
                                   DG.getString(to.getMime()),
                                   DG.getLong(to.getSize()),
                                   DG.getString(to.getPath()),
                                   System.currentTimeMillis());
    }

    @Override
    public AttachmentTO getById(Long id) {
        if (id == null) {
            return null;
        }
        List<AttachmentTO> list = jdbcTemplate.query("SELECT * FROM attachment where id = ?",
                                                     this.getRowMapper(),
                                                     id);
        if (list == null
            || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public AttachmentTO getByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        List<AttachmentTO> list = jdbcTemplate.query("SELECT * FROM attachment where name = ?",
                                                     this.getRowMapper(),
                                                     DG.getString(name));
        if (list == null
            || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<AttachmentTO> getAll() {
        return jdbcTemplate.query("SELECT * FROM attachment",
                                  this.getRowMapper());
    }

    @Override
    public List<AttachmentTO> search(AttachmentFilterTO filterTO) {
        String sql = "SELECT * FROM attachment where 1 = 1 ";
        List<Object> params = new ArrayList<Object>();
        if (StringUtils.isNotEmpty(filterTO.getName())) {
            sql += " and LCASE(name) like ? ";
            params.add("%" + filterTO.getName().toLowerCase() + "%");
        }
        if (filterTO.getMinSize() != null
            && filterTO.getMinSize() > 0) {
            sql += " and size >= ? ";
            params.add(filterTO.getMinSize());
        }
        if (filterTO.getMaxSize() != null
            && filterTO.getMaxSize() > 0) {
            sql += " and size <= ? ";
            params.add(filterTO.getMaxSize());
        }
        if (filterTO.getBeforeTime() != null
            && filterTO.getBeforeTime() > 0) {
            sql += " and created < ? ";
            params.add(filterTO.getBeforeTime());
        }
        if (filterTO.getAfterTime() != null
            && filterTO.getAfterTime() > 0) {
            sql += " and created >= ? ";
            params.add(filterTO.getAfterTime());
        }

        return jdbcTemplate.query(sql,
                                  this.getRowMapper(),
                                  params.toArray());
    }

    private RowMapper<AttachmentTO> getRowMapper() {
        return new RowMapper<AttachmentTO>() {
            public AttachmentTO mapRow(ResultSet rs, int arg1) throws SQLException {
                AttachmentTO to = new AttachmentTO();
                to.setId(DG.getLong(rs.getLong("id")));
                long created = DG.getLong(rs.getLong("created"));
                if (created > 0) {
                    to.setCreatedTime(new Date(created));
                }
                to.setName(DG.getString(rs.getString("name")));
                to.setMime(DG.getString(rs.getString("mime")));
                to.setSize(DG.getLong(rs.getLong("size")));
                to.setPath(DG.getString(rs.getString("path")));
                return to;
            }
        };
    }
}
