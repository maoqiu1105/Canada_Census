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
@Table(name="HOUSEHOLDSIZE", schema="APP")
public class HouseholdSize implements Serializable
{

    /**
     *  Generated serial version ID
     */
    private static final long serialVersionUID = 5395402102608953168L;

    @Id
    @Column(name="ID")
    short id;

    @Column(name="DESCRIPTION")
    String description;

    // bi-directional many-to-one association to Age
    @OneToMany(mappedBy="householdSize")
    private Set <Household> households = new HashSet <Household>();

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

    public Set <Household> getHouseholds()
    {

        return households;

    }

    public void setHouseholds(Set <Household> households)
    {

        this.households = households;

    }

    public Household addHousehold(Household household)
    {

        getHouseholds().add(household);
        household.setHouseholdSize(this);

        return household;

    }

    public Household removeHousehold(Household household)
    {

        getHouseholds().remove(household);
        household.setHouseholdSize(null);

        return household;

    }

}


