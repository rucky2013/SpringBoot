package com.resume.converter;

/**
 * Convertor to convert input from one type to the other.
 * 
 * @param <I> Input to converter.
 * @param <O> Output from converter.
 */
public interface Converter<I, O> {

    /**
     * Converts input from one form to the other.
     * 
     * @param input
     * @return
     */
    public O convert(I input);
}
