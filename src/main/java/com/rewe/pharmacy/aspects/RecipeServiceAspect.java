package com.rewe.pharmacy.aspects;

import com.rewe.pharmacy.data.entity.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Aspect
@Slf4j
@Component
public class RecipeServiceAspect {
    @Before(value = "execution(* com.rewe.pharmacy.service.impl.RecipeServiceImpl.*(..))")
    public void beforeRecipeServiceAdvice(JoinPoint joinPoint)  {
        log.info("Before: " + joinPoint.getSignature() + " at: " + LocalDate.now());
    }
    @Around(value =
            "execution(* com.rewe.pharmacy.service.impl.RecipeServiceImpl.getRecipes(..))")
    public  List<Recipe> aroundRecipeServiceAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Before in around: " + joinPoint.getSignature() + " start: " + LocalDate.now());
        List<Recipe> recipes = (List<Recipe>) joinPoint.proceed();
        log.info("Returned in around: " + recipes.size() + " recipes -> end: " + LocalDate.now());
        return recipes;
    }
    @After(value = "execution(* com.rewe.pharmacy.service.impl.RecipeServiceImpl.*(..))")
    public void afterRecipeServiceAdvice(JoinPoint joinPoint) {
        log.info("After: " + joinPoint.getSignature() + " at: " + LocalDate.now());
    }

    @AfterReturning(value = "execution(* com.rewe.pharmacy.service.impl.RecipeServiceImpl.*(..))", returning = "returnObject")
    public void afterReturningRecipeServiceAdvice(JoinPoint joinPoint, Object returnObject) {
        log.info("After returning: " + joinPoint.getSignature() + " returns: " + returnObject + " at: " + LocalDate.now());
    }

    @AfterThrowing(value = "execution(* com.rewe.pharmacy.service.impl.RecipeServiceImpl.*(..))", throwing = "ex")
    public void afterThrowingRecipeServiceAdvice(JoinPoint joinPoint, Exception ex)  {
        log.error("After throwing: " + joinPoint.getSignature()  + " at: " + LocalDate.now());
    }

}
