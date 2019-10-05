/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Cuotas;
import py.com.mojeda.service.ejb.entity.Modalidades;
import py.com.mojeda.service.ejb.entity.TipoCalculos;
import py.com.mojeda.service.ejb.entity.TipoDesembolsos;
import py.com.mojeda.service.ejb.manager.CuotasManager;

/**
 *
 * @author Miguel
 */
@Stateless
public class CuotasManagerImpl extends GenericDaoImpl<Cuotas, Long>
        implements CuotasManager {

    @Override
    protected Class<Cuotas> getEntityBeanType() {
        return Cuotas.class;
    }

    @Override
    public List<Cuotas> cuotasCredito(BigDecimal montoCapital, BigDecimal montoInteres, Date fechaVencimiento, Integer diaVencimiento, Short plazo,
            Short periodoCapital, Short periodoInteres, Short periodoGracia, TipoCalculos tipoCalculo, Modalidades modalidad,
            TipoDesembolsos tipoDesembolso, Date fechaGeneracion) throws Exception {
        List<Cuotas> retorno = null;
        Cuotas cuota;
        // Fecha vencimiento
        LocalDateTime fechaSistema = LocalDateTime.now();
        ZoneId currentZone = ZoneId.systemDefault();
        ZonedDateTime fechaZona = ZonedDateTime.of(fechaSistema, currentZone);
        ZonedDateTime fechaCuotas = null;
        //Calcular plazo en meses dependiento del periodo capital
        if (null != periodoCapital) {
            retorno = new ArrayList<>();
            switch (periodoCapital) {
                case 1:
                    for (int dia = 0; dia <= plazo; dia++) {
                        if (dia == 0) {
                            fechaCuotas = fechaZona.withDayOfMonth(diaVencimiento);
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(dia);
                            cuota.setFechaVencimiento(java.util.Date.from(fechaCuotas.toInstant()));
                            cuota.setCapital(BigDecimal.ZERO);
                            cuota.setInteres(BigDecimal.ZERO);
                            cuota.setInteresAdelantado(BigDecimal.ZERO);
                            cuota.setInteresDescontado(BigDecimal.ZERO);
                            cuota.setInteresPunitorio(BigDecimal.ZERO);
                            cuota.setInteresMoratorio(BigDecimal.ZERO);
                        } else {
                            fechaCuotas = fechaZona.withDayOfMonth(diaVencimiento).plusDays(dia);
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(dia);
                            cuota.setFechaVencimiento(java.util.Date.from(fechaCuotas.toInstant()));
                            cuota.setCapital(BigDecimal.ZERO);
                            cuota.setInteres(BigDecimal.ZERO);
                            cuota.setInteresAdelantado(BigDecimal.ZERO);
                            cuota.setInteresDescontado(BigDecimal.ZERO);
                            cuota.setInteresPunitorio(BigDecimal.ZERO);
                            cuota.setInteresMoratorio(BigDecimal.ZERO);
                        }
                        retorno.add(cuota);
                    }
                    break;
                case 7:
                    for (int semana = 0; semana <= plazo; semana++) {
                        if (semana == 0) {
                            fechaCuotas = fechaZona.withDayOfMonth(diaVencimiento);
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(semana);
                            cuota.setFechaVencimiento(java.util.Date.from(fechaCuotas.toInstant()));
                            cuota.setCapital(BigDecimal.ZERO);
                            cuota.setInteres(BigDecimal.ZERO);
                            cuota.setInteresAdelantado(BigDecimal.ZERO);
                            cuota.setInteresDescontado(BigDecimal.ZERO);
                            cuota.setInteresPunitorio(BigDecimal.ZERO);
                            cuota.setInteresMoratorio(BigDecimal.ZERO);
                        } else {
                            fechaCuotas = fechaZona.withDayOfMonth(diaVencimiento).plusDays(7 * semana);
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(semana);
                            cuota.setFechaVencimiento(java.util.Date.from(fechaCuotas.toInstant()));
                            cuota.setCapital(BigDecimal.ZERO);
                            cuota.setInteres(BigDecimal.ZERO);
                            cuota.setInteresAdelantado(BigDecimal.ZERO);
                            cuota.setInteresDescontado(BigDecimal.ZERO);
                            cuota.setInteresPunitorio(BigDecimal.ZERO);
                            cuota.setInteresMoratorio(BigDecimal.ZERO);
                        }
                        retorno.add(cuota);
                    }
                    break;
                case 15:
                    for (int quincenal = 0; quincenal <= plazo; quincenal++) {
                        if (quincenal == 0) {
                            fechaCuotas = fechaZona.withDayOfMonth(diaVencimiento);
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(quincenal);
                            cuota.setFechaVencimiento(java.util.Date.from(fechaCuotas.toInstant()));
                            cuota.setCapital(BigDecimal.ZERO);
                            cuota.setInteres(BigDecimal.ZERO);
                            cuota.setInteresAdelantado(BigDecimal.ZERO);
                            cuota.setInteresDescontado(BigDecimal.ZERO);
                            cuota.setInteresPunitorio(BigDecimal.ZERO);
                            cuota.setInteresMoratorio(BigDecimal.ZERO);
                        } else {
                            fechaCuotas = fechaZona.withDayOfMonth(diaVencimiento).plusDays(15 * quincenal);
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(quincenal);
                            cuota.setFechaVencimiento(java.util.Date.from(fechaCuotas.toInstant()));
                            cuota.setCapital(BigDecimal.ZERO);
                            cuota.setInteres(BigDecimal.ZERO);
                            cuota.setInteresAdelantado(BigDecimal.ZERO);
                            cuota.setInteresDescontado(BigDecimal.ZERO);
                            cuota.setInteresPunitorio(BigDecimal.ZERO);
                            cuota.setInteresMoratorio(BigDecimal.ZERO);
                        }
                        retorno.add(cuota);
                    }
                    break;
                case 30:
                    for (int mensual = 0; mensual <= plazo; mensual++) {
                        if (mensual == 0) {
                            fechaCuotas = fechaZona.withDayOfMonth(diaVencimiento);
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(mensual);
                            cuota.setFechaVencimiento(java.util.Date.from(fechaCuotas.toInstant()));
                            cuota.setCapital(BigDecimal.ZERO);
                            cuota.setInteres(BigDecimal.ZERO);
                            cuota.setInteresAdelantado(BigDecimal.ZERO);
                            cuota.setInteresDescontado(BigDecimal.ZERO);
                            cuota.setInteresPunitorio(BigDecimal.ZERO);
                            cuota.setInteresMoratorio(BigDecimal.ZERO);
                        } else {
                            
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(mensual);
                            cuota.setCapital(BigDecimal.ZERO);
                            cuota.setInteres(BigDecimal.ZERO);
                            cuota.setInteresAdelantado(BigDecimal.ZERO);
                            cuota.setInteresDescontado(BigDecimal.ZERO);
                            cuota.setInteresPunitorio(BigDecimal.ZERO);
                            cuota.setInteresMoratorio(BigDecimal.ZERO);
                            
                            if (diaVencimiento.equals(28)
                                    || diaVencimiento.equals(29)
                                    || diaVencimiento.equals(30)) {
                                YearMonth daysMonth = YearMonth.of(fechaZona.getYear(), Month.of(fechaZona.getMonthValue() + 1));
                                if (daysMonth.lengthOfMonth() <= diaVencimiento) {
                                    fechaCuotas = fechaZona.withDayOfMonth(daysMonth.lengthOfMonth()).plusMonths(mensual);
                                    cuota.setFechaVencimiento(java.util.Date.from(fechaCuotas.toInstant()));
                                } else {
                                    fechaCuotas = fechaZona.withDayOfMonth(diaVencimiento).plusMonths(mensual);
                                    cuota.setFechaVencimiento(java.util.Date.from(fechaCuotas.toInstant()));
                                }
                            }else{
                                fechaCuotas = fechaZona.withDayOfMonth(diaVencimiento).plusMonths(mensual);
                                cuota.setFechaVencimiento(java.util.Date.from(fechaCuotas.toInstant()));
                            }

                        }
                        retorno.add(cuota);
                    }
                    break;
                case 60:
                    for (int bimestral = 0; bimestral <= plazo; bimestral++) {
                        if (bimestral == 0) {
                            fechaCuotas = fechaZona.withDayOfMonth(diaVencimiento);
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(bimestral);
                            cuota.setFechaVencimiento(java.util.Date.from(fechaCuotas.toInstant()));
                            cuota.setCapital(BigDecimal.ZERO);
                            cuota.setInteres(BigDecimal.ZERO);
                            cuota.setInteresAdelantado(BigDecimal.ZERO);
                            cuota.setInteresDescontado(BigDecimal.ZERO);
                            cuota.setInteresPunitorio(BigDecimal.ZERO);
                            cuota.setInteresMoratorio(BigDecimal.ZERO);
                        } else {
                            
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(bimestral);
                            cuota.setCapital(BigDecimal.ZERO);
                            cuota.setInteres(BigDecimal.ZERO);
                            cuota.setInteresAdelantado(BigDecimal.ZERO);
                            cuota.setInteresDescontado(BigDecimal.ZERO);
                            cuota.setInteresPunitorio(BigDecimal.ZERO);
                            cuota.setInteresMoratorio(BigDecimal.ZERO);
                            
                            if (diaVencimiento.equals(28)
                                    || diaVencimiento.equals(29)
                                    || diaVencimiento.equals(30)) {
                                YearMonth daysMonth = YearMonth.of(fechaZona.getYear(), Month.of(fechaZona.getMonthValue() + 2));
                                if (daysMonth.lengthOfMonth() <= diaVencimiento) {
                                    fechaCuotas = fechaZona.withDayOfMonth(daysMonth.lengthOfMonth()).plusMonths(2 * bimestral);
                                    cuota.setFechaVencimiento(java.util.Date.from(fechaCuotas.toInstant()));
                                } else {
                                    fechaCuotas = fechaZona.withDayOfMonth(diaVencimiento).plusMonths(2 * bimestral);
                                    cuota.setFechaVencimiento(java.util.Date.from(fechaCuotas.toInstant()));
                                }
                            }else{
                                fechaCuotas = fechaZona.withDayOfMonth(diaVencimiento).plusMonths(2 * bimestral);
                                cuota.setFechaVencimiento(java.util.Date.from(fechaCuotas.toInstant()));
                            }

                        }
                        retorno.add(cuota);
                    }
                    break;
                case 90:
                    for (int trimestral = 0; trimestral <= plazo; trimestral++) {
                        if (trimestral == 0) {
                            fechaCuotas = fechaZona.withDayOfMonth(diaVencimiento);
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(trimestral);
                            cuota.setFechaVencimiento(java.util.Date.from(fechaCuotas.toInstant()));
                            cuota.setCapital(BigDecimal.ZERO);
                            cuota.setInteres(BigDecimal.ZERO);
                            cuota.setInteresAdelantado(BigDecimal.ZERO);
                            cuota.setInteresDescontado(BigDecimal.ZERO);
                            cuota.setInteresPunitorio(BigDecimal.ZERO);
                            cuota.setInteresMoratorio(BigDecimal.ZERO);
                        } else {
                            
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(trimestral);
                            cuota.setCapital(BigDecimal.ZERO);
                            cuota.setInteres(BigDecimal.ZERO);
                            cuota.setInteresAdelantado(BigDecimal.ZERO);
                            cuota.setInteresDescontado(BigDecimal.ZERO);
                            cuota.setInteresPunitorio(BigDecimal.ZERO);
                            cuota.setInteresMoratorio(BigDecimal.ZERO);
                            
                            if (diaVencimiento.equals(28)
                                    || diaVencimiento.equals(29)
                                    || diaVencimiento.equals(30)) {
                                YearMonth daysMonth = YearMonth.of(fechaZona.getYear(), Month.of(fechaZona.getMonthValue() + 3));
                                if (daysMonth.lengthOfMonth() <= diaVencimiento) {
                                    fechaCuotas = fechaZona.withDayOfMonth(daysMonth.lengthOfMonth()).plusMonths(3 * trimestral);
                                    cuota.setFechaVencimiento(java.util.Date.from(fechaCuotas.toInstant()));
                                } else {
                                    fechaCuotas = fechaZona.withDayOfMonth(diaVencimiento).plusMonths(3 * trimestral);
                                    cuota.setFechaVencimiento(java.util.Date.from(fechaCuotas.toInstant()));
                                }
                            }else{
                                fechaCuotas = fechaZona.withDayOfMonth(diaVencimiento).plusMonths(3 * trimestral);
                                cuota.setFechaVencimiento(java.util.Date.from(fechaCuotas.toInstant()));
                            }

                        }
                        retorno.add(cuota);
                    }
                    break;
                case 180:
                    for (int semestral = 0; semestral <= plazo; semestral++) {
                        if (semestral == 0) {
                            fechaCuotas = fechaZona.withDayOfMonth(diaVencimiento);
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(semestral);
                            cuota.setFechaVencimiento(java.util.Date.from(fechaCuotas.toInstant()));
                            cuota.setCapital(BigDecimal.ZERO);
                            cuota.setInteres(BigDecimal.ZERO);
                            cuota.setInteresAdelantado(BigDecimal.ZERO);
                            cuota.setInteresDescontado(BigDecimal.ZERO);
                            cuota.setInteresPunitorio(BigDecimal.ZERO);
                            cuota.setInteresMoratorio(BigDecimal.ZERO);
                        } else {
                            
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(semestral);
                            cuota.setCapital(BigDecimal.ZERO);
                            cuota.setInteres(BigDecimal.ZERO);
                            cuota.setInteresAdelantado(BigDecimal.ZERO);
                            cuota.setInteresDescontado(BigDecimal.ZERO);
                            cuota.setInteresPunitorio(BigDecimal.ZERO);
                            cuota.setInteresMoratorio(BigDecimal.ZERO);
                            
                            if (diaVencimiento.equals(28)
                                    || diaVencimiento.equals(29)
                                    || diaVencimiento.equals(30)) {
                                YearMonth daysMonth = YearMonth.of(fechaZona.getYear(), Month.of(fechaZona.getMonthValue() + 6));
                                if (daysMonth.lengthOfMonth() <= diaVencimiento) {
                                    fechaCuotas = fechaZona.withDayOfMonth(daysMonth.lengthOfMonth()).plusMonths(6 * semestral);
                                    cuota.setFechaVencimiento(java.util.Date.from(fechaCuotas.toInstant()));
                                } else {
                                    fechaCuotas = fechaZona.withDayOfMonth(diaVencimiento).plusMonths(6 * semestral);
                                    cuota.setFechaVencimiento(java.util.Date.from(fechaCuotas.toInstant()));
                                }
                            }else{
                                fechaCuotas = fechaZona.withDayOfMonth(diaVencimiento).plusMonths(6 * semestral);
                                cuota.setFechaVencimiento(java.util.Date.from(fechaCuotas.toInstant()));
                            }

                        }
                        retorno.add(cuota);
                    }
                    break;
                case 360:
                    for (int semestral = 0; semestral <= plazo; semestral++) {
                        if (semestral == 0) {
                            fechaCuotas = fechaZona.withDayOfMonth(diaVencimiento);
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(semestral);
                            cuota.setFechaVencimiento(java.util.Date.from(fechaCuotas.toInstant()));
                            cuota.setCapital(BigDecimal.ZERO);
                            cuota.setInteres(BigDecimal.ZERO);
                            cuota.setInteresAdelantado(BigDecimal.ZERO);
                            cuota.setInteresDescontado(BigDecimal.ZERO);
                            cuota.setInteresPunitorio(BigDecimal.ZERO);
                            cuota.setInteresMoratorio(BigDecimal.ZERO);
                        } else {
                            
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(semestral);
                            cuota.setCapital(BigDecimal.ZERO);
                            cuota.setInteres(BigDecimal.ZERO);
                            cuota.setInteresAdelantado(BigDecimal.ZERO);
                            cuota.setInteresDescontado(BigDecimal.ZERO);
                            cuota.setInteresPunitorio(BigDecimal.ZERO);
                            cuota.setInteresMoratorio(BigDecimal.ZERO);
                            
                            if (diaVencimiento.equals(28)
                                    || diaVencimiento.equals(29)
                                    || diaVencimiento.equals(30)) {
                                YearMonth daysMonth = YearMonth.of(fechaZona.getYear(), Month.of(fechaZona.getMonthValue() + 12));
                                if (daysMonth.lengthOfMonth() <= diaVencimiento) {
                                    fechaCuotas = fechaZona.withDayOfMonth(daysMonth.lengthOfMonth()).plusMonths(12);
                                    cuota.setFechaVencimiento(java.util.Date.from(fechaCuotas.toInstant()));
                                } else {
                                    fechaCuotas = fechaZona.withDayOfMonth(diaVencimiento).plusMonths(12);
                                    cuota.setFechaVencimiento(java.util.Date.from(fechaCuotas.toInstant()));
                                }
                            }else{
                                fechaCuotas = fechaZona.withDayOfMonth(diaVencimiento).plusMonths(12);
                                cuota.setFechaVencimiento(java.util.Date.from(fechaCuotas.toInstant()));
                            }

                        }
                        retorno.add(cuota);
                    }
                    break;
                default:
                    break;
            }
        }

        return retorno;
    }

}
