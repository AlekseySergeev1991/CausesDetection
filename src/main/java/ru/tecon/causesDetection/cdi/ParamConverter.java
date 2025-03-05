package ru.tecon.causesDetection.cdi;


import jakarta.el.ValueExpression;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import ru.tecon.causesDetection.model.ObjProp;

/**
 * Конвертер для выбора свойства объекта
 *
 * @author Aleksey Sergeev
 */
@FacesConverter("paramConverter")
public class ParamConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        ValueExpression vex = context.getApplication().getExpressionFactory()
                .createValueExpression(context.getELContext(), "#{errorDetectionMB}", CausesDetectionMB.class);

        CausesDetectionMB defaultValues = (CausesDetectionMB) vex.getValue(context.getELContext());

        return defaultValues.getPropList().stream()
                .filter(propType -> propType.getPropId() == Integer.parseInt(value))
                .findFirst()
                .orElse(null);

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((ObjProp) value).getPropId());
    }
}
