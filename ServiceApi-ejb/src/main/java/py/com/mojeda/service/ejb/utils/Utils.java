/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.utils;

import java.util.Comparator;
import py.com.mojeda.service.ejb.informconf.IfcPSolicitudesDetalleDb;

/**
 *
 * @author mojeda
 */
public class Utils {

    public static Comparator<IfcPSolicitudesDetalleDb> fechaInformconf = new Comparator<IfcPSolicitudesDetalleDb>() {
        @Override
        public int compare(IfcPSolicitudesDetalleDb o1, IfcPSolicitudesDetalleDb o2) {
            return o2.getFecha().compareTo(o1.getFecha());
        }
    };

}
