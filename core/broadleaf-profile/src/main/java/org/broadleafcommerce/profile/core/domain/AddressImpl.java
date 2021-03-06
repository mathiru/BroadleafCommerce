/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.broadleafcommerce.profile.core.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.broadleafcommerce.common.time.domain.TemporalTimestampListener;
import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.client.VisibilityEnum;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;

@Entity
@EntityListeners(value = { TemporalTimestampListener.class })
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "BLC_ADDRESS")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="blOrderElements")
public class AddressImpl implements Address {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "AddressId", strategy = GenerationType.TABLE)
    @TableGenerator(name = "AddressId", table = "SEQUENCE_GENERATOR", pkColumnName = "ID_NAME", valueColumnName = "ID_VAL", pkColumnValue = "AddressImpl", allocationSize = 50)
    @Column(name = "ADDRESS_ID")
    protected Long id;

    @Column(name = "ADDRESS_LINE1", nullable = false)
    @AdminPresentation(friendlyName="Address #1", order=6, group="Address")
    protected String addressLine1;

    @Column(name = "ADDRESS_LINE2")
    @AdminPresentation(friendlyName="Address #2", order=7, group="Address")
    protected String addressLine2;

    @Column(name = "CITY", nullable = false)
    @AdminPresentation(friendlyName="City", order=8, group="Address")
    protected String city;

    @Column(name = "POSTAL_CODE", nullable = false)
    @AdminPresentation(friendlyName="Postal Code", order=14, group="Address")
    protected String postalCode;

    @Column(name = "COUNTY")
    @AdminPresentation(friendlyName="County", order=11, group="Address")
    protected String county;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = StateImpl.class)
    @JoinColumn(name = "STATE_PROV_REGION")
    @Index(name="ADDRESS_STATE_INDEX", columnNames={"STATE_PROV_REGION"})
    @AdminPresentation(friendlyName="State", order=9, group="Address", excluded = true)
    protected State state;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = CountryImpl.class, optional = false)
    @JoinColumn(name = "COUNTRY")
    @Index(name="ADDRESS_COUNTRY_INDEX", columnNames={"COUNTRY"})
    @AdminPresentation(friendlyName="Country", order=12, group="Address", excluded = true)
    protected Country country;
    
    /**
     * This is intented to be used for address verification integrations and should not be modifiable in the admin
     */
    @Column(name = "TOKENIZED_ADDRESS")
    @AdminPresentation(friendlyName="Tokenized Address", order=15, group="Address", visibility=VisibilityEnum.HIDDEN_ALL)
    protected String tokenizedAddress;
    
    /**
     * This is intented to be used for address verification integrations and should not be modifiable in the admin
     */
    @Column(name = "STANDARDIZED")
    @AdminPresentation(friendlyName="Standardized", order=16, group="Address", visibility=VisibilityEnum.HIDDEN_ALL)
    protected Boolean standardized = Boolean.FALSE;

    @Column(name = "ZIP_FOUR")
    @AdminPresentation(friendlyName="Four Digit Zip", order=17, group="Address")
    protected String zipFour;

    @Column(name = "COMPANY_NAME")
    @AdminPresentation(friendlyName="Company Name", order=3, group="Address")
    protected String companyName;

    @Column(name = "IS_DEFAULT")
    @AdminPresentation(friendlyName="Default Address", order=18, group="Address")
    protected boolean isDefault = false;

    @Column(name = "IS_ACTIVE")
    @AdminPresentation(friendlyName="Active Address", order=19, group="Address")
    protected boolean isActive = true;

    @Column(name = "FIRST_NAME")
    @AdminPresentation(friendlyName="First Name", order=1, group="Address")
    protected String firstName;

    @Column(name = "LAST_NAME")
    @AdminPresentation(friendlyName="Last Name", order=2, group="Address")
    protected String lastName;

    @Column(name = "PRIMARY_PHONE")
    @AdminPresentation(friendlyName="Primary Phone", order=4, group="Address")
    protected String primaryPhone;

    @Column(name = "SECONDARY_PHONE")
    @AdminPresentation(friendlyName="Secondary Phone", order=5, group="Address")
    protected String secondaryPhone;

    @Column(name = "IS_BUSINESS")
    @AdminPresentation(friendlyName="Business Address", order=20, group="Address")
    protected boolean isBusiness = false;
    
    /**
     * This is intented to be used for address verification integrations and should not be modifiable in the admin
     */
    @Column(name = "VERIFICATION_LEVEL")
    @AdminPresentation(friendlyName="Verification Level", order=21, group="Address", visibility=VisibilityEnum.HIDDEN_ALL)
    protected String verificationLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getTokenizedAddress() {
        return tokenizedAddress;
    }

    public void setTokenizedAddress(String tokenizedAddress) {
        this.tokenizedAddress = tokenizedAddress;
    }

    public Boolean getStandardized() {
        return standardized;
    }

    public void setStandardized(Boolean standardized) {
        this.standardized = standardized;
    }

    public String getZipFour() {
        return zipFour;
    }

    public void setZipFour(String zipFour) {
        this.zipFour = zipFour;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    public String getSecondaryPhone() {
        return secondaryPhone;
    }

    public void setSecondaryPhone(String secondaryPhone) {
        this.secondaryPhone = secondaryPhone;
    }

    public boolean isBusiness() {
        return isBusiness;
    }

    public void setBusiness(boolean isBusiness) {
        this.isBusiness = isBusiness;
    }

    public String getVerificationLevel() {
        return verificationLevel;
    }

    public void setVerificationLevel(String verificationLevel) {
        this.verificationLevel = verificationLevel;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AddressImpl other = (AddressImpl) obj;

        if (id != null && other.id != null) {
            return id.equals(other.id);
        }

        if (addressLine1 == null) {
            if (other.addressLine1 != null)
                return false;
        } else if (!addressLine1.equals(other.addressLine1))
            return false;
        if (addressLine2 == null) {
            if (other.addressLine2 != null)
                return false;
        } else if (!addressLine2.equals(other.addressLine2))
            return false;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (companyName == null) {
            if (other.companyName != null)
                return false;
        } else if (!companyName.equals(other.companyName))
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (county == null) {
            if (other.county != null)
                return false;
        } else if (!county.equals(other.county))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (postalCode == null) {
            if (other.postalCode != null)
                return false;
        } else if (!postalCode.equals(other.postalCode))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((addressLine1 == null) ? 0 : addressLine1.hashCode());
        result = prime * result + ((addressLine2 == null) ? 0 : addressLine2.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((county == null) ? 0 : county.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        return result;
    }
}
