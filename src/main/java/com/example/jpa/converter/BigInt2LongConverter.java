package com.example.jpa.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.math.BigInteger;

@Converter(autoApply = true)
public class BigInt2LongConverter implements AttributeConverter<BigInteger, Long> {
    /**
     * Converts the value stored in the entity attribute into the
     * data representation to be stored in the database.
     *
     * @param attribute the entity attribute value to be converted
     * @return the converted data to be stored in the database
     * column
     */
    @Override
    public Long convertToDatabaseColumn(BigInteger attribute) {
        return attribute == null ? 0L : attribute.longValue();
    }
    
    /**
     * Converts the data stored in the database column into the
     * value to be stored in the entity attribute.
     * Note that it is the responsibility of the converter writer to
     * specify the correct {@code dbData} type for the corresponding
     * column for use by the JDBC driver: i.e., persistence providers are
     * not expected to do such type conversion.
     *
     * @param dbData the data from the database column to be
     *               converted
     * @return the converted value to be stored in the entity
     * attribute
     */
    @Override
    public BigInteger convertToEntityAttribute(Long dbData) {
        return dbData == null ? BigInteger.ZERO : BigInteger.valueOf(dbData);
    }
}
