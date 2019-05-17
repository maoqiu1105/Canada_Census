package prog3060.wang.CanadaCensusDB;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="GEOGRAPHICAREA", schema="APP")
public class GeographicArea implements Serializable
{

    /**
     *  Generated serial version ID
     */
    private static final long serialVersionUID = 7940612392411560658L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="GEOGRAPHICAREAID")
    int geographicAreaID;

    @Column(name="CODE")
    int code;

    @Column(name="LEVEL")
    int level;

    @Column(name="NAME")
    String name;

    @Column(name="ALTERNATIVECODE")
    int alternativeCode;

    // bi-directional many-to-one association to Age
    @OneToMany(mappedBy="geographicArea")
    private Set <Age> ages = new HashSet <Age>();

    public GeographicArea()
    {}

    public int getGeographicAreaID()
    {

        return geographicAreaID;

    }

    public void setGeographicAreaID(int geographicAreaID)
    {

        this.geographicAreaID = geographicAreaID;

    }

    public int getCode()
    {

        return code;

    }

    public void setCode(int code)
    {

        this.code = code;

    }

    public int getLevel()
    {

        return level;

    }

    public void setLevel(int level)
    {

        this.level = level;

    }

    public String getName()
    {

        return name;

    }

    public void setName(String name)
    {

        this.name = name;

    }

    public int getAlternativeCode()
    {

        return alternativeCode;

    }

    public void setAlternativeCode(int alternativeCode)
    {

        this.alternativeCode = alternativeCode;

    }

    public Set <Age> getAges()
    {

        return ages;

    }

    public void setAges(Set <Age> ages)
    {

        this.ages = ages;

    }

    public Age addAge(Age age)
    {

        getAges().add(age);
        age.setGeographicArea(this);

        return age;

    }

    public Age removeAge(Age age)
    {

        getAges().remove(age);
        age.setGeographicArea(null);

        return age;
    }

}



