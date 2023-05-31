package com.quathar.contactbook.data.embeddable;

import com.quathar.contactbook.data.enumerator.TelephoneType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h1>Telephone</h1>
 * 
 * @since 2022-04-03
 * @version 2.0
 * @author Q
 */
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Telephone {

	// <<-FIELDS->>
	@Enumerated(EnumType.STRING)
	private TelephoneType type;
	private String number;

}
