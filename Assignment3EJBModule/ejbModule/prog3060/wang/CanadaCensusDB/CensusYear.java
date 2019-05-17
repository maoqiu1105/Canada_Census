package prog3060.wang.CanadaCensusDB;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CENSUSYEAR", schema="APP")
public class CensusYear implements Serializable
{

    /**
     *  Generated serial version ID
     */
    private static final long serialVersionUID = 4944270801453125336L;

    @Id
    @Column(name="CENSUSYEARID")
    int censusYearID;

    @Column(name="CENSUSYEAR")
    int censusYear;

    // bi-directional many-to-one association to Age
    @OneToMany(mappedBy="censusYear")
    private Set <Age> ages = new HashSet <Age>();

    public int getCensusYearID()
    {

        return censusYearID;

    }

    public void setCensusYearID(int censusYearID)
    {

        this.censusYearID = censusYearID;

    }

    public int getCensusYear()
    {

        return censusYear;

    }

    public void setCensusYear(int censusYear)
    {

        this.censusYear = censusYear;

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
        age.setCensusYear(this);

        return age;

    }

    public Age removeAge(Age age)
    {

        getAges().remove(age);
        age.setCensusYear(null);

        return age;
    }

}



