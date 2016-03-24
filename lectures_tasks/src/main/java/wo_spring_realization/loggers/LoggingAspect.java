package wo_spring_realization.loggers;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class LoggingAspect {

	private Map<Class<?>, Integer> counter = new HashMap<Class<?>, Integer>();

	@Pointcut("execution(* *.logEvent(..))")
	private void allLogEventMethods(){}

	@AfterReturning("allLogEventMethods()")
	public void count(JoinPoint joinPoint){
		Class<?> clazz = joinPoint.getTarget().getClass();
		if(!counter.containsKey(clazz)){
			counter.put(clazz, 0);
		}
		System.out.println(String.format("[ASPECT LOG]: %s, %d", clazz, counter.get(clazz)+1));
		counter.put(clazz, counter.get(clazz)+1);
	}
}
