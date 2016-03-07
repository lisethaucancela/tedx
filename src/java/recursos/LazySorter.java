/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import java.util.Comparator;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Máximo Mussini
 */
public class LazySorter<T> implements Comparator<T> {

    private String sortField;
    private SortOrder sortOrder;

    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @Override
    public int compare(Object first, Object second) {
        try {
            Object value1 = ReflectionUtil.getValue(first, sortField);
            Object value2 = ReflectionUtil.getValue(second, sortField);

            int value = 0;

            if (value1 instanceof Comparable) {
                value = ((Comparable) value1).compareTo(value2);
            } else if (value1 instanceof Boolean && !value1.equals(value2)) {
                value = (Boolean) value1 ? 1 : -1;
            }

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
