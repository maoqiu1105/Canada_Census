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
@Table(name="AGEGROUP", schema="APP")
public class AgeGroup implements Serializable
{

    /**
     *  Generated serial version ID
     */
    private static final long serialVersionUID = 5419866121610951099L;

    @Id
    @Column(name="AGEGROUPID")
    int ageGroupID;

    @Column(name="DESCRIPTION")
    String description;

    // bi-directional many-to-one association to Age
    @OneToMany(mappedBy="ageGroup")
    private Set <Age> ages = new HashSet <Age>();

    public int getAgeGroupID()
    {

        return ageGroupID;

    }

    public void setAgeGroupID(int ageGroupID)
    {

        this.ageGroupID = ageGroupID;

    }

    public String getDescription()
    {

        return description;

    }

    public void setDescription(String description)
    {

        this.description = description;

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
        age.setAgeGroup(this);

        return age;

    }

    public Age removeAge(Age age)
    {

        getAges().remove(age);
        age.setAgeGroup(null);

        return age;
    }

}



