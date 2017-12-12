package com.sac.basics;

/**
 * In java, a method can only be written in Subclass, not in same class.
 * 
 * Overriding is runtime polymorphism as on run time we decide which method is
 * going to execute
 * 
 * Can't override static method as non static , otherwise we will get compile time error.
 * 
 * if both are static then it is method hiding,where compile time resolution is done.
 * 
 * Variable overriding is compile time hence no overriding may be static or non static
 * 
 * The return type should be the same or a subtype of the return type declared
 * in the original overridden method in the super class. (only for objects not
 * prmitives)
 * 
 * 
 * Overridden not applicable to private methods,however we can delcare exactly
 * same method in child class but not overriding
 * 
 * The access level cannot be more restrictive than the overridden method’s
 * access level. Non final can be overridden as final
 * For example: if the super class method is declared public then
 * the overridding method in the sub class cannot be either private or
 * protected.
 * 
 * 
 * An overriding method can throw any uncheck exceptions, regardless of whether
 * the overridden method throws exceptions or not. However the overriding method
 * should not throw checked exceptions that are new or broader than the ones
 * declared by the overridden method.
 * 
 * The overriding method can throw narrower or fewer exceptions than the
 * overridden method. Constructors cannot be overridde
 * 
 * @author ssachdev
 *
 */
public class Overrriding {

}
