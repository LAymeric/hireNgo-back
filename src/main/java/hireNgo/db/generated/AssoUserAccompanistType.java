package hireNgo.db.generated;

import javax.annotation.Generated;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.querydsl.sql.Column;

/**
 * AssoUserAccompanistType is a Querydsl bean type
 */
@Generated("com.coreoz.plume.db.querydsl.generation.IdBeanSerializer")
public class AssoUserAccompanistType {

    @JsonSerialize(using=com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Column("id_accompanist_type")
    private Long idAccompanistType;

    @JsonSerialize(using=com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Column("id_user")
    private Long idUser;

    public Long getIdAccompanistType() {
        return idAccompanistType;
    }

    public void setIdAccompanistType(Long idAccompanistType) {
        this.idAccompanistType = idAccompanistType;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

}

