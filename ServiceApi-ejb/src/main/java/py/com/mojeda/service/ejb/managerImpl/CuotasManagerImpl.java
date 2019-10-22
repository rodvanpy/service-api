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
import py.com.mojeda.service.ejb.utils.ApplicationLogger;

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

    private static final ApplicationLogger logger = ApplicationLogger.getInstance();
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

    }
    
        @Override
    public Long calcularCuota(Double modalidad, Double plazo, Long periodoCapital, Long vencimientoInteres, Double tasaInteres, Double montoSolicitado, TipoCalculos tipoCalculoImporte, Double gastosAdministrativos) {
        try {
            
            Double interes = ((gastosAdministrativos == null ? 0 : gastosAdministrativos) + tasaInteres) / 100;
            
            if (tipoCalculoImporte != null && "TC-2".equals(tipoCalculoImporte.getCodigo())) {

                Double valor_1 = ((montoSolicitado * interes) / 36500) * vencimientoInteres;

                Long valor_2 = Math.round(Math.pow((1 + ((interes / 36500) * vencimientoInteres)), plazo)) - 1;

                Long valor_3 = Math.round(Math.pow((1 + ((interes / 36500) * vencimientoInteres)), plazo));

                Double valor_4 = valor_1 / valor_2;

                //this.myForm.controls['importeCuota'].setValue(Math.round(valor_4 * valor_3));
                return (Math.round(valor_4 * valor_3));

            } else if (tipoCalculoImporte != null && "TC-4".equals(tipoCalculoImporte.getCodigo())) {

                //Interés simple (i) = Capital (c) x Tipo de Interés (r) x Tiempo (t)
                //• Si la duración es 3 años, t = 3
                //• Si la duración es 18 meses, t = 18 / 12 = 1,5
                //• Si la duración es 1 año, t = 1
                //• Si la duración es 6 meses, t = 6 / 12 = 0,5
                //• Si la duración es 1 día, t = 1 / 365

                Double periodoInteres = this.periodoInteresSimple(plazo, interes, periodoCapital, vencimientoInteres);

                Double montoInteres = montoSolicitado * periodoInteres * plazo;

                Double montoTotal = montoSolicitado + montoInteres;

                Long montoCuota = Math.round(montoTotal / plazo);

                //this.myForm.controls['importeCuota'].setValue();
                return montoCuota;

            } else if (tipoCalculoImporte != null && "TC-5".equals(tipoCalculoImporte.getCodigo())) {

                Double periodoInteres = this.periodoInteresCompuesto(plazo, interes, periodoCapital, vencimientoInteres);

                Double montoInteres = montoSolicitado * periodoInteres;

                Double montoTotal = montoSolicitado + montoInteres;

                Long montoCuota = Math.round(montoTotal / plazo);

                //this.myForm.controls['importeCuota'].setValue(Math.round(montoCuota));
                return montoCuota;
            } else if (tipoCalculoImporte != null && "TC-6".equals(tipoCalculoImporte.getCodigo())) {

                Double montoInteres = montoSolicitado * interes;

                Double montoTotal = montoSolicitado + montoInteres;

                Long montoCuota = Math.round(montoTotal / plazo);

                return montoCuota;
            }
        } catch (Exception e) {
            logger.error("Error al calcularCuota", e);
        }
        return null;
    }

    @Override
    public Long calcularMontoInteres(Double plazo, Long periodoCapital, Long vencimientoInteres, Double tasaInteres, Double montoSolicitado, TipoCalculos tipoCalculoImporte, Double gastosAdministrativos) {
         try {
             
             Double interes = ((gastosAdministrativos == null ? 0 : gastosAdministrativos) + tasaInteres) / 100;
             
            if (tipoCalculoImporte != null && "TC-2".equals(tipoCalculoImporte.getCodigo())) {

                Double valor_1 = ((montoSolicitado * interes) / 36500) * vencimientoInteres;

                Long valor_2 = Math.round(Math.pow((1 + ((interes / 36500) * vencimientoInteres)), plazo)) - 1;

                Long valor_3 = Math.round(Math.pow((1 + ((interes / 36500) * vencimientoInteres)), plazo));

                Double valor_4 = valor_1 / valor_2;

                //this.myForm.controls['importeCuota'].setValue(Math.round(valor_4 * valor_3));
                return (Math.round(valor_4 * valor_3));

            } else if (tipoCalculoImporte != null && "TC-4".equals(tipoCalculoImporte.getCodigo())) {

                //Interés simple (i) = Capital (c) x Tipo de Interés (r) x Tiempo (t)
                //• Si la duración es 3 años, t = 3
                //• Si la duración es 18 meses, t = 18 / 12 = 1,5
                //• Si la duración es 1 año, t = 1
                //• Si la duración es 6 meses, t = 6 / 12 = 0,5
                //• Si la duración es 1 día, t = 1 / 365

                Double periodoInteres = this.periodoInteresSimple(plazo, interes, periodoCapital, vencimientoInteres);

                Double montoInteres = montoSolicitado * periodoInteres * plazo;

                return montoInteres.longValue();

            } else if (tipoCalculoImporte != null && "TC-5".equals(tipoCalculoImporte.getCodigo())) {

                Double periodoInteres = this.periodoInteresCompuesto(plazo, interes, periodoCapital, vencimientoInteres);

                Double montoInteres = montoSolicitado * periodoInteres;

                return montoInteres.longValue();

            } else if (tipoCalculoImporte != null && "TC-6".equals(tipoCalculoImporte.getCodigo())) {

                Double montoInteres = montoSolicitado * interes;

                return montoInteres.longValue();
            }
        } catch (Exception e) {
            logger.error("Error al calcularMontoInteres", e);
        }
        return null;
    }
    
    public Double periodoInteresSimple(Double plazo, Double interes, Long periodoCapital, Long vencimientoInteres) {
        try {
            Double periodoInteres = 0.0;

            if (vencimientoInteres == 30) {

                periodoInteres = interes / 12;

            } else if (vencimientoInteres == 0) {

                if (periodoCapital == 60) {

                    periodoInteres = interes / 6;

                } else if (periodoCapital == 90) {

                    periodoInteres = interes / 4;

                } else if (periodoCapital == 180) {

                    periodoInteres = interes / 2;

                } else if (periodoCapital == 360) {

                    periodoInteres = interes / 1;

                } else if (periodoCapital == 15) {

                    periodoInteres = interes / 24;

                } else if (periodoCapital == 1) {

                    periodoInteres = interes / 36;

                } else if (periodoCapital == 30) {

                    periodoInteres = interes / 12;

                }
            }

            return periodoInteres;
        } catch (Exception e) {
            logger.error("Error al periodoInteresSimple", e);
        }
        return null;

    }
    
    public Double periodoInteresCompuesto(Double plazo, Double interes, Long periodoCapital, Long vencimientoInteres) {
        try {
            Double periodoInteres = 0.0;
            if (vencimientoInteres == 30) {

                periodoInteres = (Math.pow((1 + interes), plazo / 12)) - 1;

            } else if (vencimientoInteres == 0) {

                if (periodoCapital == 60) {

                    periodoInteres = (Math.pow((1 + (interes / 6)), ((plazo / 12) * 6))) - 1;

                } else if (periodoCapital == 90) {

                    periodoInteres = (Math.pow((1 + (interes / 4)), ((plazo / 12) * 4))) - 1;

                } else if (periodoCapital == 180) {

                    periodoInteres = (Math.pow((1 + (interes / 2)), ((plazo / 12) * 2))) - 1;

                } else if (periodoCapital == 360) {

                    periodoInteres = (Math.pow((1 + interes), plazo / 12)) - 1;

                } else if (periodoCapital == 15) {

                    periodoInteres = interes / 24;

                } else if (periodoCapital == 1) {

                    periodoInteres = (Math.pow((1 + (interes / 2)), ((plazo / 12) * 2))) - 1;

                } else if (periodoCapital == 30) {

                    periodoInteres = (Math.pow((1 + interes), plazo / 12)) - 1;

                }
            }
            return periodoInteres;
        } catch (Exception e) {
            logger.error("Error al periodoInteresSimple", e);
        }
        return null;

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
