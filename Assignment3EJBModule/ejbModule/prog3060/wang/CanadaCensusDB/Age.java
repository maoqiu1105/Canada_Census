package prog3060.wang.CanadaCensusDB;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="AGE", schema="APP")
public class Age implements Serializable
{

    /**
     *  Generated serial version ID
     */
    private static final long serialVersionUID = 1801029030939952234L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="AGEID")
    int ageID;

    @ManyToOne
    @JoinColumn(name="AGEGROUP")
    AgeGroup ageGroup;

    @ManyToOne
    @JoinColumn(name="CENSUSYEAR")
    CensusYear censusYear;

    @ManyToOne
    @JoinColumn(name="GEOGRAPHICAREA")
    GeographicArea geographicArea;

    @Column(name="COMBINED")
    int combined;

    @Column(name="MALE")
    int male;

    @Column(name="FEMALE")
    int female;

    public Age()
    {}

    public int getAgeID()
    {

        return ageID;

    }

    public void setAgeID(int ageID)
    {

        this.ageID = ageID;

    }

    public AgeGroup getAgeGroup()
    {

        return ageGroup;

    }

    public void setAgeGroup(AgeGroup ageGroup)
    {

        this.ageGroup = ageGroup;

    }

    public CensusYear getCensusYear()
    {

        return censusYear;

    }

    public void setCensusYear(CensusYear censusYear)
    {

        this.censusYear = censusYear;

    }

    public GeographicArea getGeographicArea()
    {

        return geographicArea;

    }

    public void setGeographicArea(GeographicArea geographicArea)
    {

        this.geographicArea = geographicArea;

    }

    public int getCombined()
    {

        return combined;

    }

    public void setCombined(int combined)
    {

        this.combined = combined;

    }

    public int getMale()
    {

        return male;

    }

    public void setMale(int male)
    {

        this.male = male;

    }

    public int getFemale()
    {

        return female;

    }

    public void setFemale(int female)
    {

        this.female = female;

    }

}



