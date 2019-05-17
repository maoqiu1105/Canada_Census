package prog3060.wang.CanadaCensusDB;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TOTALINCOME", schema="APP")
public class TotalIncome implements Serializable
{

    /**
     *  Generated serial version ID
     */
    private static final long serialVersionUID = -7956010811710018875L;

    @Id
    @Column(name="ID")
    short id;

    @Column(name="DESCRIPTION")
    String description;

    public short getId()
    {

        return id;

    }

    public void setId(short id)
    {

        this.id = id;

    }

    public String getDescription()
    {

        return description;

    }

    public void setDescription(String description)
    {

        this.description = description;

    }

}



