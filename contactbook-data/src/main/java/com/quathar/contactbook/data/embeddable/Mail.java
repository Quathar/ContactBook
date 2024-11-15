package com.quathar.contactbook.data.embeddable;

import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h1>Mail</h1>
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
public class Mail {

	private String name;

}
