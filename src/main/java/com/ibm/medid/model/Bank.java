package com.ibm.medid.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "m_bank")
public class Bank extends BaseProperties {

    private String name;

    @Column(name = "va_code")
    private String vaCode;

}
