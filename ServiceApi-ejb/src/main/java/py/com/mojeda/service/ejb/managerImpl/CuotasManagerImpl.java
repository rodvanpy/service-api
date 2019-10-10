/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
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

    protected static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    protected static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public List<Cuotas> cuotasCredito(BigDecimal montoCapital, BigDecimal montoInteres, Date fechaVencimiento, Integer diaVencimiento, Short plazo,
            Short periodoCapital, Short periodoInteres, Short periodoGracia, TipoCalculos tipoCalculo, Modalidades modalidad,
            TipoDesembolsos tipoDesembolso, Date fechaGeneracion) throws Exception {
        //Generar fecha Cuotas
        List<Cuotas> listaCuotas = this.fechaCuotasCapitalInteres(plazo, periodoCapital, periodoInteres, new Date(System.currentTimeMillis()), diaVencimiento);
        //Cargar Amortizaciones Capital
        this.amortizacionCuotas(listaCuotas, montoCapital, plazo, periodoCapital, periodoInteres);

        return listaCuotas;
        //Calcular plazo en meses dependiento del periodo capital
//        if (null != periodoCapital) {
//            retorno = new ArrayList<>();
//            switch (periodoCapital) {
//                case 1:
//                    for (int dia = 0; dia <= plazo; dia++) {
//                        if (dia == 0) {
//                            fechaInicio = fechaZona.withDayOfMonth(diaVencimiento);
//                            cuota = new Cuotas();
//                            cuota.setNumeroCuota(dia);
//                            cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
//                            cuota.setCapital(BigDecimal.ZERO);
//                            cuota.setInteres(BigDecimal.ZERO);
//                            cuota.setInteresAdelantado(BigDecimal.ZERO);
//                            cuota.setInteresDescontado(BigDecimal.ZERO);
//                            cuota.setInteresPunitorio(BigDecimal.ZERO);
//                            cuota.setInteresMoratorio(BigDecimal.ZERO);
//                        } else {
//                            fechaInicio = fechaZona.withDayOfMonth(diaVencimiento).plusDays(dia);
//                            cuota = new Cuotas();
//                            cuota.setNumeroCuota(dia);
//                            cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
//                            cuota.setCapital(new BigDecimal(amortizacion));
//                            cuota.setInteres(BigDecimal.ZERO);
//                            cuota.setInteresAdelantado(BigDecimal.ZERO);
//                            cuota.setInteresDescontado(BigDecimal.ZERO);
//                            cuota.setInteresPunitorio(BigDecimal.ZERO);
//                            cuota.setInteresMoratorio(BigDecimal.ZERO);
//
//                            //Suma de amortizaciones
//                            amortizacionTotal = amortizacionTotal + amortizacion;
//                            //Calcular diferencia con el capital total y sumar a la ultima cuota
//                            if (dia == plazo) {
//                                if (amortizacionTotal < montoCapital.longValue()) {
//                                    Long diferenciaAmortizacion = montoCapital.longValue() - amortizacionTotal;
//                                    cuota.setCapital(new BigDecimal(amortizacion + diferenciaAmortizacion));
//                                }
//                            }
//                        }
//                        retorno.add(cuota);
//                    }
//                    break;
//                case 7:
//                    for (int semana = 0; semana <= plazo; semana++) {
//                        if (semana == 0) {
//                            fechaInicio = fechaZona.withDayOfMonth(diaVencimiento);
//                            cuota = new Cuotas();
//                            cuota.setNumeroCuota(semana);
//                            cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
//                            cuota.setCapital(BigDecimal.ZERO);
//                            cuota.setInteres(BigDecimal.ZERO);
//                            cuota.setInteresAdelantado(BigDecimal.ZERO);
//                            cuota.setInteresDescontado(BigDecimal.ZERO);
//                            cuota.setInteresPunitorio(BigDecimal.ZERO);
//                            cuota.setInteresMoratorio(BigDecimal.ZERO);
//                        } else {
//                            fechaInicio = fechaZona.withDayOfMonth(diaVencimiento).plusDays(7 * semana);
//                            cuota = new Cuotas();
//                            cuota.setNumeroCuota(semana);
//                            cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
//                            cuota.setCapital(new BigDecimal(amortizacion));
//                            cuota.setInteres(BigDecimal.ZERO);
//                            cuota.setInteresAdelantado(BigDecimal.ZERO);
//                            cuota.setInteresDescontado(BigDecimal.ZERO);
//                            cuota.setInteresPunitorio(BigDecimal.ZERO);
//                            cuota.setInteresMoratorio(BigDecimal.ZERO);
//
//                            //Suma de amortizaciones
//                            amortizacionTotal = amortizacionTotal + amortizacion;
//                            //Calcular diferencia con el capital total y sumar a la ultima cuota
//                            if (semana == plazo) {
//                                if (amortizacionTotal < montoCapital.longValue()) {
//                                    Long diferenciaAmortizacion = montoCapital.longValue() - amortizacionTotal;
//                                    cuota.setCapital(new BigDecimal(amortizacion + diferenciaAmortizacion));
//                                }
//                            }
//                        }
//                        retorno.add(cuota);
//                    }
//                    break;
//                case 15:
//                    for (int quincenal = 0; quincenal <= plazo; quincenal++) {
//                        if (quincenal == 0) {
//                            fechaInicio = fechaZona.withDayOfMonth(diaVencimiento);
//                            cuota = new Cuotas();
//                            cuota.setNumeroCuota(quincenal);
//                            cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
//                            cuota.setCapital(new BigDecimal(amortizacion));
//                            cuota.setInteres(BigDecimal.ZERO);
//                            cuota.setInteresAdelantado(BigDecimal.ZERO);
//                            cuota.setInteresDescontado(BigDecimal.ZERO);
//                            cuota.setInteresPunitorio(BigDecimal.ZERO);
//                            cuota.setInteresMoratorio(BigDecimal.ZERO);
//                        } else {
//                            fechaInicio = fechaZona.withDayOfMonth(diaVencimiento).plusDays(15 * quincenal);
//                            cuota = new Cuotas();
//                            cuota.setNumeroCuota(quincenal);
//                            cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
//                            cuota.setCapital(new BigDecimal(amortizacion));
//                            cuota.setInteres(BigDecimal.ZERO);
//                            cuota.setInteresAdelantado(BigDecimal.ZERO);
//                            cuota.setInteresDescontado(BigDecimal.ZERO);
//                            cuota.setInteresPunitorio(BigDecimal.ZERO);
//                            cuota.setInteresMoratorio(BigDecimal.ZERO);
//
//                            //Suma de amortizaciones
//                            amortizacionTotal = amortizacionTotal + amortizacion;
//                            //Calcular diferencia con el capital total y sumar a la ultima cuota
//                            if (quincenal == plazo) {
//                                if (amortizacionTotal < montoCapital.longValue()) {
//                                    Long diferenciaAmortizacion = montoCapital.longValue() - amortizacionTotal;
//                                    cuota.setCapital(new BigDecimal(amortizacion + diferenciaAmortizacion));
//                                }
//                            }
//                        }
//                        retorno.add(cuota);
//                    }
//                    break;
//                case 30:
//                    for (int mensual = 0; mensual <= plazo; mensual++) {
//                        if (mensual == 0) {
//                            fechaInicio = fechaZona.withDayOfMonth(diaVencimiento);
//                            cuota = new Cuotas();
//                            cuota.setNumeroCuota(mensual);
//                            cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
//                            cuota.setCapital(BigDecimal.ZERO);
//                            cuota.setInteres(BigDecimal.ZERO);
//                            cuota.setInteresAdelantado(BigDecimal.ZERO);
//                            cuota.setInteresDescontado(BigDecimal.ZERO);
//                            cuota.setInteresPunitorio(BigDecimal.ZERO);
//                            cuota.setInteresMoratorio(BigDecimal.ZERO);
//                        } else {
//
//                            cuota = new Cuotas();
//                            cuota.setNumeroCuota(mensual);
//                            cuota.setCapital(new BigDecimal(amortizacion));
//                            cuota.setInteres(BigDecimal.ZERO);
//                            cuota.setInteresAdelantado(BigDecimal.ZERO);
//                            cuota.setInteresDescontado(BigDecimal.ZERO);
//                            cuota.setInteresPunitorio(BigDecimal.ZERO);
//                            cuota.setInteresMoratorio(BigDecimal.ZERO);
//
//                            if (diaVencimiento.equals(28)
//                                    || diaVencimiento.equals(29)
//                                    || diaVencimiento.equals(30)) {
//                                YearMonth daysMonth = YearMonth.of(fechaZona.getYear(), Month.of(fechaZona.getMonthValue() + 1));
//                                if (daysMonth.lengthOfMonth() <= diaVencimiento) {
//                                    fechaInicio = fechaZona.withDayOfMonth(daysMonth.lengthOfMonth()).plusMonths(mensual);
//                                    cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
//                                } else {
//                                    fechaInicio = fechaZona.withDayOfMonth(diaVencimiento).plusMonths(mensual);
//                                    cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
//                                }
//                            } else {
//                                fechaInicio = fechaZona.withDayOfMonth(diaVencimiento).plusMonths(mensual);
//                                cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
//                            }
//
//                            //Suma de amortizaciones
//                            amortizacionTotal = amortizacionTotal + amortizacion;
//                            //Calcular diferencia con el capital total y sumar a la ultima cuota
//                            if (mensual == plazo) {
//                                if (amortizacionTotal < montoCapital.longValue()) {
//                                    Long diferenciaAmortizacion = montoCapital.longValue() - amortizacionTotal;
//                                    cuota.setCapital(new BigDecimal(amortizacion + diferenciaAmortizacion));
//                                }
//                            }
//
//                        }
//                        retorno.add(cuota);
//                    }
//                    break;
//                case 60:
//                    for (int bimestral = 0; bimestral <= plazo; bimestral++) {
//                        if (bimestral == 0) {
//                            fechaInicio = fechaZona.withDayOfMonth(diaVencimiento);
//                            cuota = new Cuotas();
//                            cuota.setNumeroCuota(bimestral);
//                            cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
//                            cuota.setCapital(BigDecimal.ZERO);
//                            cuota.setInteres(BigDecimal.ZERO);
//                            cuota.setInteresAdelantado(BigDecimal.ZERO);
//                            cuota.setInteresDescontado(BigDecimal.ZERO);
//                            cuota.setInteresPunitorio(BigDecimal.ZERO);
//                            cuota.setInteresMoratorio(BigDecimal.ZERO);
//                        } else {
//
//                            cuota = new Cuotas();
//                            cuota.setNumeroCuota(bimestral);
//                            cuota.setCapital(new BigDecimal(amortizacion));
//                            cuota.setInteres(BigDecimal.ZERO);
//                            cuota.setInteresAdelantado(BigDecimal.ZERO);
//                            cuota.setInteresDescontado(BigDecimal.ZERO);
//                            cuota.setInteresPunitorio(BigDecimal.ZERO);
//                            cuota.setInteresMoratorio(BigDecimal.ZERO);
//
//                            if (diaVencimiento.equals(28)
//                                    || diaVencimiento.equals(29)
//                                    || diaVencimiento.equals(30)) {
//                                YearMonth daysMonth = YearMonth.of(fechaZona.getYear(), Month.of(fechaZona.getMonthValue() + 2));
//                                if (daysMonth.lengthOfMonth() <= diaVencimiento) {
//                                    fechaInicio = fechaZona.withDayOfMonth(daysMonth.lengthOfMonth()).plusMonths(2 * bimestral);
//                                    cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
//                                } else {
//                                    fechaInicio = fechaZona.withDayOfMonth(diaVencimiento).plusMonths(2 * bimestral);
//                                    cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
//                                }
//                            } else {
//                                fechaInicio = fechaZona.withDayOfMonth(diaVencimiento).plusMonths(2 * bimestral);
//                                cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
//                            }
//
//                            //Suma de amortizaciones
//                            amortizacionTotal = amortizacionTotal + amortizacion;
//                            //Calcular diferencia con el capital total y sumar a la ultima cuota
//                            if (bimestral == plazo) {
//                                if (amortizacionTotal < montoCapital.longValue()) {
//                                    Long diferenciaAmortizacion = montoCapital.longValue() - amortizacionTotal;
//                                    cuota.setCapital(new BigDecimal(amortizacion + diferenciaAmortizacion));
//                                }
//                            }
//                        }
//                        retorno.add(cuota);
//                    }
//                    break;
//                case 90:
//                    for (int trimestral = 0; trimestral <= plazo; trimestral++) {
//                        if (trimestral == 0) {
//                            fechaInicio = fechaZona.withDayOfMonth(diaVencimiento);
//                            cuota = new Cuotas();
//                            cuota.setNumeroCuota(trimestral);
//                            cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
//                            cuota.setCapital(BigDecimal.ZERO);
//                            cuota.setInteres(BigDecimal.ZERO);
//                            cuota.setInteresAdelantado(BigDecimal.ZERO);
//                            cuota.setInteresDescontado(BigDecimal.ZERO);
//                            cuota.setInteresPunitorio(BigDecimal.ZERO);
//                            cuota.setInteresMoratorio(BigDecimal.ZERO);
//                        } else {
//
//                            cuota = new Cuotas();
//                            cuota.setNumeroCuota(trimestral);
//                            cuota.setCapital(new BigDecimal(amortizacion));
//                            cuota.setInteres(BigDecimal.ZERO);
//                            cuota.setInteresAdelantado(BigDecimal.ZERO);
//                            cuota.setInteresDescontado(BigDecimal.ZERO);
//                            cuota.setInteresPunitorio(BigDecimal.ZERO);
//                            cuota.setInteresMoratorio(BigDecimal.ZERO);
//
//                            if (diaVencimiento.equals(28)
//                                    || diaVencimiento.equals(29)
//                                    || diaVencimiento.equals(30)) {
//                                YearMonth daysMonth = YearMonth.of(fechaZona.getYear(), Month.of(fechaZona.getMonthValue() + 3));
//                                if (daysMonth.lengthOfMonth() <= diaVencimiento) {
//                                    fechaInicio = fechaZona.withDayOfMonth(daysMonth.lengthOfMonth()).plusMonths(3 * trimestral);
//                                    cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
//                                } else {
//                                    fechaInicio = fechaZona.withDayOfMonth(diaVencimiento).plusMonths(3 * trimestral);
//                                    cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
//                                }
//                            } else {
//                                fechaInicio = fechaZona.withDayOfMonth(diaVencimiento).plusMonths(3 * trimestral);
//                                cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
//                            }
//
//                            //Suma de amortizaciones
//                            amortizacionTotal = amortizacionTotal + amortizacion;
//                            //Calcular diferencia con el capital total y sumar a la ultima cuota
//                            if (trimestral == plazo) {
//                                if (amortizacionTotal < montoCapital.longValue()) {
//                                    Long diferenciaAmortizacion = montoCapital.longValue() - amortizacionTotal;
//                                    cuota.setCapital(new BigDecimal(amortizacion + diferenciaAmortizacion));
//                                }
//                            }
//                        }
//                        retorno.add(cuota);
//                    }
//                    break;
//                case 180:
//                    for (int semestral = 0; semestral <= plazo; semestral++) {
//                        if (semestral == 0) {
//                            fechaInicio = fechaZona.withDayOfMonth(diaVencimiento);
//                            cuota = new Cuotas();
//                            cuota.setNumeroCuota(semestral);
//                            cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
//                            cuota.setCapital(BigDecimal.ZERO);
//                            cuota.setInteres(BigDecimal.ZERO);
//                            cuota.setInteresAdelantado(BigDecimal.ZERO);
//                            cuota.setInteresDescontado(BigDecimal.ZERO);
//                            cuota.setInteresPunitorio(BigDecimal.ZERO);
//                            cuota.setInteresMoratorio(BigDecimal.ZERO);
//                        } else {
//
//                            cuota = new Cuotas();
//                            cuota.setNumeroCuota(semestral);
//                            cuota.setCapital(new BigDecimal(amortizacion));
//                            cuota.setInteres(BigDecimal.ZERO);
//                            cuota.setInteresAdelantado(BigDecimal.ZERO);
//                            cuota.setInteresDescontado(BigDecimal.ZERO);
//                            cuota.setInteresPunitorio(BigDecimal.ZERO);
//                            cuota.setInteresMoratorio(BigDecimal.ZERO);
//
//                            if (diaVencimiento.equals(28)
//                                    || diaVencimiento.equals(29)
//                                    || diaVencimiento.equals(30)) {
//                                YearMonth daysMonth = YearMonth.of(fechaZona.getYear(), Month.of(fechaZona.getMonthValue() + 6));
//                                if (daysMonth.lengthOfMonth() <= diaVencimiento) {
//                                    fechaInicio = fechaZona.withDayOfMonth(daysMonth.lengthOfMonth()).plusMonths(6 * semestral);
//                                    cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
//                                } else {
//                                    fechaInicio = fechaZona.withDayOfMonth(diaVencimiento).plusMonths(6 * semestral);
//                                    cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
//                                }
//                            } else {
//                                fechaInicio = fechaZona.withDayOfMonth(diaVencimiento).plusMonths(6 * semestral);
//                                cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
//                            }
//
//                            //Suma de amortizaciones
//                            amortizacionTotal = amortizacionTotal + amortizacion;
//                            //Calcular diferencia con el capital total y sumar a la ultima cuota
//                            if (semestral == plazo) {
//                                if (amortizacionTotal < montoCapital.longValue()) {
//                                    Long diferenciaAmortizacion = montoCapital.longValue() - amortizacionTotal;
//                                    cuota.setCapital(new BigDecimal(amortizacion + diferenciaAmortizacion));
//                                }
//                            }
//
//                        }
//                        retorno.add(cuota);
//                    }
//                    break;
//                case 360:
//                    for (int semestral = 0; semestral <= plazo; semestral++) {
//                        if (semestral == 0) {
//                            fechaInicio = fechaZona.withDayOfMonth(diaVencimiento);
//                            cuota = new Cuotas();
//                            cuota.setNumeroCuota(semestral);
//                            cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
//                            cuota.setCapital(BigDecimal.ZERO);
//                            cuota.setInteres(BigDecimal.ZERO);
//                            cuota.setInteresAdelantado(BigDecimal.ZERO);
//                            cuota.setInteresDescontado(BigDecimal.ZERO);
//                            cuota.setInteresPunitorio(BigDecimal.ZERO);
//                            cuota.setInteresMoratorio(BigDecimal.ZERO);
//                        } else {
//
//                            cuota = new Cuotas();
//                            cuota.setNumeroCuota(semestral);
//                            cuota.setCapital(new BigDecimal(amortizacion));
//                            cuota.setInteres(BigDecimal.ZERO);
//                            cuota.setInteresAdelantado(BigDecimal.ZERO);
//                            cuota.setInteresDescontado(BigDecimal.ZERO);
//                            cuota.setInteresPunitorio(BigDecimal.ZERO);
//                            cuota.setInteresMoratorio(BigDecimal.ZERO);
//
//                            if (diaVencimiento.equals(28)
//                                    || diaVencimiento.equals(29)
//                                    || diaVencimiento.equals(30)) {
//                                YearMonth daysMonth = YearMonth.of(fechaZona.getYear(), Month.of(fechaZona.getMonthValue() + 12));
//                                if (daysMonth.lengthOfMonth() <= diaVencimiento) {
//                                    fechaInicio = fechaZona.withDayOfMonth(daysMonth.lengthOfMonth()).plusMonths(12);
//                                    cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
//                                } else {
//                                    fechaInicio = fechaZona.withDayOfMonth(diaVencimiento).plusMonths(12);
//                                    cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
//                                }
//                            } else {
//                                fechaInicio = fechaZona.withDayOfMonth(diaVencimiento).plusMonths(12);
//                                cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
//                            }
//
//                            //Suma de amortizaciones
//                            amortizacionTotal = amortizacionTotal + amortizacion;
//                            //Calcular diferencia con el capital total y sumar a la ultima cuota
//                            if (semestral == plazo) {
//                                if (amortizacionTotal < montoCapital.longValue()) {
//                                    Long diferenciaAmortizacion = montoCapital.longValue() - amortizacionTotal;
//                                    cuota.setCapital(new BigDecimal(amortizacion + diferenciaAmortizacion));
//                                }
//                            }
//
//                        }
//                        retorno.add(cuota);
//                    }
//                    break;
//                default:
//                    break;
//            }
//        }
//
//        return retorno;
    }

    public void amortizacionCuotas(List<Cuotas> cuotas, BigDecimal montoCapital, Short plazo,
            Short periodoCapital, Short periodoInteres) {
        //Calcular amortizacion del capital
        Long amortizacion = montoCapital.longValue() / plazo.longValue();
        Long amortizacionTotal = 0L;
        Long plazoIntervalo;
        Long index = 1L;
        Long indexCuotas = 0L;
        Integer intervaloCuotas;
        //Calcular plazo en meses dependiento del periodo capital
        if (null != periodoCapital) {
            switch (periodoCapital) {
                case 1:
                    for (Cuotas rpc : cuotas) {
                        rpc.setCapital(rpc.getNumeroCuota() == 0 ? BigDecimal.ZERO : new BigDecimal(amortizacion));
                        amortizacionTotal = amortizacionTotal + amortizacion;
                    }
                    break;
                case 7:
                    for (Cuotas rpc : cuotas) {
                        rpc.setCapital(rpc.getNumeroCuota() == 0 ? BigDecimal.ZERO : new BigDecimal(amortizacion));
                        amortizacionTotal = amortizacionTotal + amortizacion;
                    }
                    break;
                case 15:
                    for (Cuotas rpc : cuotas) {
                        rpc.setCapital(rpc.getNumeroCuota() == 0 ? BigDecimal.ZERO : new BigDecimal(amortizacion));
                        amortizacionTotal = amortizacionTotal + amortizacion;
                    }
                    break;
                case 30:
                    for (Cuotas rpc : cuotas) {
                        rpc.setCapital(rpc.getNumeroCuota() == 0 ? BigDecimal.ZERO : new BigDecimal(amortizacion));
                        amortizacionTotal = amortizacionTotal + amortizacion;
                    }
                    break;
                case 60:
                    intervaloCuotas = 2;
                    plazoIntervalo = plazo.longValue() / 2;
                    for (Cuotas rpc : cuotas) {
                        if (Objects.equals(periodoCapital, periodoInteres)) {
                            rpc.setCapital(rpc.getNumeroCuota() == 0 ? BigDecimal.ZERO : new BigDecimal(amortizacion));
                            amortizacionTotal = rpc.getNumeroCuota() == 0 ? 0 : amortizacionTotal + amortizacion;
                            //Sumar en la ultima cuota
                            if (Objects.equals(indexCuotas, plazo.longValue())) {
                                rpc.setCapital(new BigDecimal(amortizacion + (montoCapital.longValue() - amortizacionTotal)));
                            };
                            indexCuotas++;
                        } else {
                            if (Objects.equals(rpc.getNumeroCuota(), intervaloCuotas)) {

                                amortizacion = montoCapital.longValue() / plazoIntervalo;

                                rpc.setCapital(rpc.getNumeroCuota() == 0 ? BigDecimal.ZERO : new BigDecimal(amortizacion));
                                amortizacionTotal = amortizacionTotal + amortizacion;

                                intervaloCuotas = intervaloCuotas + 2;
                                //Sumar en la ultima cuota
                                if (Objects.equals(index, plazoIntervalo)) {
                                    rpc.setCapital(new BigDecimal(amortizacion + (montoCapital.longValue() - amortizacionTotal)));
                                };
                                index++;
                            } else {
                                rpc.setCapital(BigDecimal.ZERO);
                            }
                        }
                    }
                    break;
                case 90:
                    intervaloCuotas = 3;
                    plazoIntervalo = plazo.longValue() / 3;
                    for (Cuotas rpc : cuotas) {
                        if (Objects.equals(periodoCapital, periodoInteres)) {
                            rpc.setCapital(rpc.getNumeroCuota() == 0 ? BigDecimal.ZERO : new BigDecimal(amortizacion));
                            amortizacionTotal = rpc.getNumeroCuota() == 0 ? 0 : amortizacionTotal + amortizacion;
                            //Sumar en la ultima cuota
                            if (Objects.equals(indexCuotas, plazo.longValue())) {
                                rpc.setCapital(new BigDecimal(amortizacion + (montoCapital.longValue() - amortizacionTotal)));
                            };
                            indexCuotas++;
                        } else {
                            if (Objects.equals(rpc.getNumeroCuota(), intervaloCuotas)) {

                                amortizacion = montoCapital.longValue() / plazoIntervalo;
                                rpc.setCapital(rpc.getNumeroCuota() == 0 ? BigDecimal.ZERO : new BigDecimal(amortizacion));
                                amortizacionTotal = amortizacionTotal + amortizacion;

                                intervaloCuotas = intervaloCuotas + 3;
                                //Sumar en la ultima cuota
                                if (Objects.equals(index, plazoIntervalo)) {
                                    rpc.setCapital(new BigDecimal(amortizacion + (montoCapital.longValue() - amortizacionTotal)));
                                };
                                index++;
                            } else {
                                rpc.setCapital(BigDecimal.ZERO);
                            }
                        }
                    }
                    break;
                case 180:
                    intervaloCuotas = 6;
                    plazoIntervalo = plazo.longValue() / 6;
                    for (Cuotas rpc : cuotas) {
                        if (Objects.equals(periodoCapital, periodoInteres)) {
                            rpc.setCapital(rpc.getNumeroCuota() == 0 ? BigDecimal.ZERO : new BigDecimal(amortizacion));
                            amortizacionTotal = rpc.getNumeroCuota() == 0 ? 0 : amortizacionTotal + amortizacion;
                            //Sumar en la ultima cuota
                            if (Objects.equals(indexCuotas, plazo.longValue())) {
                                rpc.setCapital(new BigDecimal(amortizacion + (montoCapital.longValue() - amortizacionTotal)));
                            };
                            indexCuotas++;
                        } else {
                            if (Objects.equals(rpc.getNumeroCuota(), intervaloCuotas)) {

                                amortizacion = montoCapital.longValue() / plazoIntervalo;
                                rpc.setCapital(rpc.getNumeroCuota() == 0 ? BigDecimal.ZERO : new BigDecimal(amortizacion));
                                amortizacionTotal = amortizacionTotal + amortizacion;

                                intervaloCuotas = intervaloCuotas + 6;
                                //Sumar en la ultima cuota
                                if (Objects.equals(index, plazoIntervalo)) {
                                    rpc.setCapital(new BigDecimal(amortizacion + (montoCapital.longValue() - amortizacionTotal)));
                                };
                                index++;
                            } else {
                                rpc.setCapital(BigDecimal.ZERO);
                            }
                        }
                    }
                    break;
                case 360:
                    intervaloCuotas = 12;
                    plazoIntervalo = plazo.longValue() / 12;
                    for (Cuotas rpc : cuotas) {
                        if (Objects.equals(periodoCapital, periodoInteres)) {
                            rpc.setCapital(rpc.getNumeroCuota() == 0 ? BigDecimal.ZERO : new BigDecimal(amortizacion));
                            amortizacionTotal = rpc.getNumeroCuota() == 0 ? 0 : amortizacionTotal + amortizacion;
                            //Sumar en la ultima cuota
                            if (Objects.equals(indexCuotas, plazo.longValue())) {
                                rpc.setCapital(new BigDecimal(amortizacion + (montoCapital.longValue() - amortizacionTotal)));
                            };
                            indexCuotas++;
                        } else {
                            if (Objects.equals(rpc.getNumeroCuota(), intervaloCuotas)) {

                                amortizacion = montoCapital.longValue() / plazoIntervalo;
                                rpc.setCapital(rpc.getNumeroCuota() == 0 ? BigDecimal.ZERO : new BigDecimal(amortizacion));
                                amortizacionTotal = amortizacionTotal + amortizacion;

                                intervaloCuotas = intervaloCuotas + 12;
                                //Sumar en la ultima cuota
                                if (Objects.equals(index, plazoIntervalo)) {
                                    rpc.setCapital(new BigDecimal(amortizacion + (montoCapital.longValue() - amortizacionTotal)));
                                };
                                index++;
                            } else {
                                rpc.setCapital(BigDecimal.ZERO);
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public List<Cuotas> fechaCuotasCapitalInteres(Short plazo, Short periodoCapital, Short periodoInteres,
            Date fechaVencimiento, Integer diaVencimiento) throws ParseException {
        List<Cuotas> retorno = null;
        Cuotas cuota;
        // Fecha vencimiento
        LocalDateTime fechaSistema = LocalDateTime.now();
        ZoneId currentZone = ZoneId.systemDefault();
        ZonedDateTime fechaZona = ZonedDateTime.of(fechaSistema, currentZone);
        //Fecha Vencimiento
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(fechaVencimiento);
        int year = calendar.get(Calendar.YEAR);
        //Add one to month {0 - 11}
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        ZonedDateTime fechaInicio = fechaZona.withDayOfMonth(diaVencimiento).withMonth(month).withYear(year);
        //Calcular plazo en meses dependiento del periodo capital
        if (null != periodoCapital) {
            retorno = new ArrayList<>();
            switch (periodoCapital) {
                case 1:
                    for (int dia = 0; dia <= plazo; dia++) {
                        if (dia == 0) {
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(dia);
                            cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.toInstant()))));
                        } else {
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(dia);
                            cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.plusDays(dia).toInstant()))));
                        }
                        retorno.add(cuota);
                    }
                    break;
                case 7:
                    for (int semana = 0; semana <= plazo; semana++) {
                        if (semana == 0) {
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(semana);
                            cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.toInstant()))));
                        } else {
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(semana);
                            cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.plusDays(7 * semana).toInstant()))));
                        }
                        retorno.add(cuota);
                    }
                    break;
                case 15:
                    for (int quincenal = 0; quincenal <= plazo; quincenal++) {
                        if (quincenal == 0) {
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(quincenal);
                            cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.toInstant()))));
                        } else {
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(quincenal);
                            cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.plusDays(7 * quincenal).toInstant()))));
                        }
                        retorno.add(cuota);
                    }
                    break;
                case 30:
                    for (int mensual = 0; mensual <= plazo; mensual++) {
                        if (mensual == 0) {
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(mensual);
                            cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.toInstant()))));
                        } else {

                            cuota = new Cuotas();
                            cuota.setNumeroCuota(mensual);
                            if (diaVencimiento.equals(28)
                                    || diaVencimiento.equals(29)
                                    || diaVencimiento.equals(30)) {
                                YearMonth daysMonth = YearMonth.of(fechaZona.getYear(), Month.of(fechaZona.getMonthValue() + 1));
                                if (daysMonth.lengthOfMonth() < diaVencimiento) {
                                    cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(daysMonth.lengthOfMonth()).plusMonths(mensual).toInstant()))));
                                } else {
                                    cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(diaVencimiento).plusMonths(mensual).toInstant()))));
                                }
                            } else {
                                cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(diaVencimiento).plusMonths(mensual).toInstant()))));
                            }
                        }
                        retorno.add(cuota);
                    }
                    break;
                case 60:
                    for (int mensual = 0; mensual <= plazo; mensual++) {
                        if (mensual == 0) {
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(mensual);
                            cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.toInstant()))));
                        } else {
                            cuota = new Cuotas();
                            //Verificar si los periodos son iguales
                            if (Objects.equals(periodoCapital, periodoInteres)) {
                                cuota.setNumeroCuota(mensual);
                                if (diaVencimiento.equals(28)
                                        || diaVencimiento.equals(29)
                                        || diaVencimiento.equals(30)) {
                                    YearMonth daysMonth = YearMonth.of(fechaZona.getYear(), Month.of(fechaZona.getMonthValue() + 2));
                                    if (daysMonth.lengthOfMonth() <= diaVencimiento) {
                                        cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(daysMonth.lengthOfMonth()).plusMonths(2 * mensual).toInstant()))));
                                    } else {
                                        cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(diaVencimiento).plusMonths(2 * mensual).toInstant()))));
                                    }
                                } else {
                                    cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(diaVencimiento).plusMonths(2 * mensual).toInstant()))));
                                }
                            } else {
                                cuota.setNumeroCuota(mensual);
                                if (diaVencimiento.equals(28)
                                        || diaVencimiento.equals(29)
                                        || diaVencimiento.equals(30)) {
                                    YearMonth daysMonth = YearMonth.of(fechaZona.getYear(), Month.of(fechaZona.getMonthValue() + 1));
                                    if (daysMonth.lengthOfMonth() < diaVencimiento) {
                                        cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(daysMonth.lengthOfMonth()).plusMonths(mensual).toInstant()))));
                                    } else {
                                        cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(diaVencimiento).plusMonths(mensual).toInstant()))));
                                    }
                                } else {
                                    cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(diaVencimiento).plusMonths(mensual).toInstant()))));
                                }
                            }
                        }
                        retorno.add(cuota);
                    }
                    break;
                case 90:
                    for (int mensual = 0; mensual <= plazo; mensual++) {
                        if (mensual == 0) {
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(mensual);
                            cuota.setFechaVencimiento(java.util.Date.from(fechaInicio.toInstant()));
                        } else {

                            cuota = new Cuotas();
                            if (Objects.equals(periodoCapital, periodoInteres)) {
                                cuota.setNumeroCuota(mensual);
                                if (diaVencimiento.equals(28)
                                        || diaVencimiento.equals(29)
                                        || diaVencimiento.equals(30)) {
                                    YearMonth daysMonth = YearMonth.of(fechaZona.getYear(), Month.of(fechaZona.getMonthValue() + 3));
                                    if (daysMonth.lengthOfMonth() <= diaVencimiento) {
                                        cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(daysMonth.lengthOfMonth()).plusMonths(3 * mensual).toInstant()))));
                                    } else {
                                        cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(diaVencimiento).plusMonths(3 * mensual).toInstant()))));
                                    }
                                } else {
                                    cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(diaVencimiento).plusMonths(3 * mensual).toInstant()))));
                                }
                            } else {
                                cuota.setNumeroCuota(mensual);
                                if (diaVencimiento.equals(28)
                                        || diaVencimiento.equals(29)
                                        || diaVencimiento.equals(30)) {
                                    YearMonth daysMonth = YearMonth.of(fechaZona.getYear(), Month.of(fechaZona.getMonthValue() + 1));
                                    if (daysMonth.lengthOfMonth() < diaVencimiento) {
                                        cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(daysMonth.lengthOfMonth()).plusMonths(mensual).toInstant()))));
                                    } else {
                                        cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(diaVencimiento).plusMonths(mensual).toInstant()))));
                                    }
                                } else {
                                    cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(diaVencimiento).plusMonths(mensual).toInstant()))));
                                }
                            }

                        }
                        retorno.add(cuota);
                    }
                    break;
                case 180:
                    for (int mensual = 0; mensual <= plazo; mensual++) {
                        if (mensual == 0) {
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(mensual);
                            cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.toInstant()))));
                        } else {
                            cuota = new Cuotas();
                            if (Objects.equals(periodoCapital, periodoInteres)) {
                                cuota.setNumeroCuota(mensual);
                                if (diaVencimiento.equals(28)
                                        || diaVencimiento.equals(29)
                                        || diaVencimiento.equals(30)) {
                                    YearMonth daysMonth = YearMonth.of(fechaZona.getYear(), Month.of(fechaZona.getMonthValue() + 6));
                                    if (daysMonth.lengthOfMonth() <= diaVencimiento) {
                                        cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(daysMonth.lengthOfMonth()).plusMonths(6 * mensual).toInstant()))));
                                    } else {
                                        cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(diaVencimiento).plusMonths(6 * mensual).toInstant()))));
                                    }
                                } else {
                                    cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(diaVencimiento).plusMonths(6 * mensual).toInstant()))));
                                }
                            } else {
                                cuota.setNumeroCuota(mensual);
                                if (diaVencimiento.equals(28)
                                        || diaVencimiento.equals(29)
                                        || diaVencimiento.equals(30)) {
                                    YearMonth daysMonth = YearMonth.of(fechaZona.getYear(), Month.of(fechaZona.getMonthValue() + 1));
                                    if (daysMonth.lengthOfMonth() < diaVencimiento) {
                                        cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(daysMonth.lengthOfMonth()).plusMonths(mensual).toInstant()))));
                                    } else {
                                        cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(diaVencimiento).plusMonths(mensual).toInstant()))));
                                    }
                                } else {
                                    cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(diaVencimiento).plusMonths(mensual).toInstant()))));
                                }
                            }
                        }
                        retorno.add(cuota);
                    }
                    break;
                case 360:
                    for (int mensual = 0; mensual <= plazo; mensual++) {
                        if (mensual == 0) {
                            cuota = new Cuotas();
                            cuota.setNumeroCuota(mensual);
                            cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.toInstant()))));
                        } else {
                            cuota = new Cuotas();
                            if (Objects.equals(periodoCapital, periodoInteres)) {
                                cuota.setNumeroCuota(mensual);
                                if (diaVencimiento.equals(28)
                                        || diaVencimiento.equals(29)
                                        || diaVencimiento.equals(30)) {
                                    YearMonth daysMonth = YearMonth.of(fechaZona.getYear(), Month.of(fechaZona.getMonthValue() + 12));
                                    if (daysMonth.lengthOfMonth() <= diaVencimiento) {
                                        cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(daysMonth.lengthOfMonth()).plusMonths(12).toInstant()))));
                                    } else {
                                        cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(diaVencimiento).plusMonths(12).toInstant()))));
                                    }
                                } else {
                                    cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(diaVencimiento).plusMonths(12).toInstant()))));
                                }
                            } else {
                                cuota.setNumeroCuota(mensual);
                                if (diaVencimiento.equals(28)
                                        || diaVencimiento.equals(29)
                                        || diaVencimiento.equals(30)) {
                                    YearMonth daysMonth = YearMonth.of(fechaZona.getYear(), Month.of(fechaZona.getMonthValue() + 1));
                                    if (daysMonth.lengthOfMonth() < diaVencimiento) {
                                        cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(daysMonth.lengthOfMonth()).plusMonths(mensual).toInstant()))));
                                    } else {
                                        cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(diaVencimiento).plusMonths(mensual).toInstant()))));
                                    }
                                } else {
                                    cuota.setFechaVencimiento(dateFormat.parse(formatter.format(java.util.Date.from(fechaInicio.withDayOfMonth(diaVencimiento).plusMonths(mensual).toInstant()))));
                                }
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
