package hireNgo.db.generated;

import javax.annotation.Generated;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.querydsl.sql.Column;

/**
 * AssoAccompanistUserService is a Querydsl bean type
 */
@Generated("com.coreoz.plume.db.querydsl.generation.IdBeanSerializer")
public class AssoAccompanistUserService {

    @JsonSerialize(using=com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Column("id_accompanist_user")
    private Long idAccompanistUser;

    @JsonSerialize(using=com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Column("id_service")
    private Long idService;

    public Long getIdAccompanistUser() {
        return idAccompanistUser;
    }

    public void setIdAccompanistUser(Long idAccompanistUser) {
        this.idAccompanistUser = idAccompanistUser;
    }

    public Long getIdService() {
        return idService;
    }

    public void setIdService(Long idService) {
        this.idService = idService;
    }

}

