package spring.validation;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public abstract class Validator<T> {
	
	@Autowired
    private MessageSource messageSource;
	
	/**
	 * @param object
	 * @return
	 */
	public abstract List<ErrorField> validate(T object);
	
	/**
	 * @param field
	 * @param value
	 * @return
	 */
	public ErrorField validate(String field, String value) {
			Class<?> clazz = this.getClass();
			try {
				String[] names = field.replaceAll("\\[[0-9]+\\]", "").split("\\.");
				StringBuilder sb = new StringBuilder("validate");
				for (String name : names) {
					sb.append(name.substring(0, 1).toUpperCase());
					sb.append(name.substring(1));
				}
				Method method = clazz.getMethod(sb.toString(), String.class, String.class);
				return (ErrorField) method.invoke(this, field, value);
			} catch (Exception e) {
				return null;
			}
	}
	
	/**
	 * @param code
	 * @param args
	 * @return
	 */
	protected String getMessage(String code, Object... args) {
		return messageSource.getMessage(code, args, Locale.getDefault());
	}

}
