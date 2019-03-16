package py.com.mojeda.service.ejb.managerImpl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.ejb.HibernateEntityManager;

import com.googlecode.genericdao.search.ExampleOptions;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.hibernate.HibernateMetadataUtil;
import com.googlecode.genericdao.search.jpa.JPASearchProcessor;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.mojeda.service.ejb.manager.GenericDao;


public abstract class GenericDaoImpl<T, ID extends Serializable> implements
        GenericDao<T, ID> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericDaoImpl.class);

    protected JPASearchProcessor processor;

    @PersistenceContext
    private EntityManager em;

    public GenericDaoImpl() {
    }

    protected abstract Class<T> getEntityBeanType();

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public EntityManager getEm() {

        if (em == null) {
            throw new IllegalStateException("EntityManager no esta seteado");
        }

        return em;
    }

    protected SessionFactory getSessionFactory() {

        if (this.em.getDelegate() instanceof HibernateEntityManager) {
            return ((HibernateEntityManager) this.getEm().getDelegate())
                    .getSession().getSessionFactory();
        } else {
            return ((Session) this.getEm().getDelegate()).getSessionFactory();
        }

    }

    private Session getSession() {

        if (this.em.getDelegate() instanceof HibernateEntityManager) {
            return ((HibernateEntityManager) this.getEm().getDelegate())
                    .getSession();
        } else {
            return ((Session) this.getEm().getDelegate());
        }

    }

//    @Override
//    public long countAll(List<CountAll> params) {
//
//        String querys = getQuery(params);
//
//        LOGGER.info(querys);
//
//        final Query query = this.em.createQuery(querys);
//
//        return (Long) query.getSingleResult();
//    }
    @Override
    public List<T> listCartera(Long estado, String tipoPersona) {
        
        final StringBuffer queryString = new StringBuffer(
                "SELECT o from ");
        queryString.append(getEntityBeanType().getSimpleName()).append(" o ");
        queryString.append("where o.").append("estado").append(" = ").append(estado).append(" ");
        queryString.append("and o.").append("tipoPersona").append(" = '").append(tipoPersona).append("' ");
        Query query = null;
       
        try {
            query = this.em.createQuery(queryString.toString());
        } catch (Exception e) {
            return (List<T>) null;
        }       
        return (List<T>) query.getResultList();
    }

    @Override
    public T getIdHandler(ID id) {

        final StringBuffer queryString = new StringBuffer(
                "SELECT o from ");
        queryString.append(getEntityBeanType().getSimpleName()).append(" o ");
        queryString.append("where o.").append("controlCarteraInformconfPK.cedulaIdentidad").append(" = '").append(id).append("'");

        final Query query = this.em.createQuery(queryString.toString());

        return (T) query.getSingleResult();
    }

    @Override
    public int total(T ejemplo) {
        return list(ejemplo, true).size();
    }

    @Override
    public T get(ID id) {
        return (T) getEm().find(getEntityBeanType(), id);
    }

    @Override
    public Map<String, Object> getLike(T ejemplo, String[] atributos) {

        List<Map<String, Object>> lista = this.listAtributos(ejemplo,
                atributos);

        if (lista.isEmpty()) {
            return null;
        } else if (lista.size() == 1) {
            return lista.get(0);
        }

        throw new NonUniqueResultException("Se encontro mas de un "
                + this.getEntityBeanType().getCanonicalName()
                + " para el pedido dado");
    }

    @Override
    public T get(T ejemplo) {
        List<T> list = this.list(ejemplo, false, 0, 1, null,
                null, false,
                false, null, null, null,
                null, null, null, null, null, false);

        if (list.isEmpty()) {
            return null;
        } else if (list.size() == 1) {
            return list.get(0);
        }

        throw new NonUniqueResultException("Se encontro mas de un "
                + this.getEntityBeanType().getCanonicalName()
                + " para el pedido dado");
    }

    @Override
    public Map<String, Object> getAtributos(T ejemplo, String[] atributos) {
        List<Map<String, Object>> lista = this.listAtributos(ejemplo,
                atributos);

        if (lista.isEmpty()) {
            return null;
        }

        if (lista.size() == 1) {
            return lista.get(0);
        }

        throw new NonUniqueResultException("Se encontro mas de un "
                + this.getEntityBeanType().getCanonicalName()
                + " para el pedido dado");
    }

    @Override
    public Map<String, Object> getAtributos(T ejemplo, String[] atributos, boolean like, boolean caseSensitive) {
        List<Map<String, Object>> lista = this.listAtributos(ejemplo, atributos, true, 0, 2,
                null, null, like, caseSensitive, null, null, null, null, null, null, null, null, null, null, false);

        if (lista.isEmpty()) {
            return null;
        }

        if (lista.size() == 1) {
            return lista.get(0);
        }

        throw new NonUniqueResultException("Se encontro mas de un "
                + this.getEntityBeanType().getCanonicalName()
                + " para el pedido dado");
    }

    @Override
    public void save(T entity) throws Exception {
        this.getEm().persist(entity);
    }

    @Override
    public void update(T entity) throws Exception {
        this.getSession().update(entity);
    }

    @Override
    public void updateApliUsuarios(String[] ids) throws Exception {
        String update = " UPDATE ADMIN.APLICACIONES_USUARIOS  "
                + " set COD_PERSONA =  " + ids[3] + ", "
                + " COD_APLICACION =  " + ids[4] + ", "
                + " PG_ENTIDAD = " + ids[5] + ""
                + " where COD_PERSONA = " + ids[0]
                + " and COD_APLICACION = " + ids[1]
                + " and PG_ENTIDAD = " + ids[2];

        Query cq = this.getEm().createNativeQuery(update);
        int retorno = cq.executeUpdate();
    }

    @Override
    public void delete(ID id) throws Exception {
        T entity = this.get(id);

        if (entity != null) {
            this.getEm().remove(entity);
        }

    }

    @Override
    public void delete(T entity) throws Exception {
        if (entity != null) {
            this.getEm().remove(entity);
        }

    }

    @Override
    public void updateRetorno(String[] ids) throws Exception {
        String update = " UPDATE admin.COD_RET_EMISORA  "
                + " set DESC_COD_RET =  '" + ids[2] + "', "
                + " ALERTA =  '" + ids[3] + "', "
                + " COD_ESTADO = '" + ids[4] + "'"
                + " where COD_SERVICIO = " + ids[0]
                + " and COD_RET = " + ids[1];

        Query cq = this.getEm().createNativeQuery(update);
        int retorno = cq.executeUpdate();
    }

    @Override
    public int updateApi(String[] ids) throws Exception {
        String update = " UPDATE admin.API_SERVICIO_CAMPO  "
                + " set COD_CAMPO =  " + ids[2] + ", "
                + " IN_OUT =  '" + ids[3] + "', "
                + " TIPO_SERVICIO =  " + ids[4] + ", "
                + " TIPO_TRX = '" + ids[5] + "'"
                + " where COD_CAMPO = " + ids[0]
                + " and COD_SERVICIO = " + ids[1];

        Query cq = this.getEm().createNativeQuery(update);
        int retorno = cq.executeUpdate();

        return retorno;
    }

    @Override
    public List<T> list() {
        return this.list(null, true, null, null, null, null, false,
                false, null, null, null,
                null, null, null, null, null, false);
    }

    @Override
    public List<T> list(Integer primerResultado, Integer cantResultado) {
        return this.list(null, false, primerResultado, cantResultado, null,
                null, false,
                false, null, null, null,
                null, null, null, null, null, false);
    }

    @Override
    public List<T> list(T ejemplo) {
        return this.list(ejemplo, true);
    }

    @Override
    public List<T> list(T ejemplo, boolean like) {
        return this.list(ejemplo, true, null, null, null, null, like, false,
                null, null, null,
                null, null, null, null,
                null, false);
    }

    @Override
    public List<T> list(T ejemplo, String orderByAttrList, String orderByDirList) {
        return this.list(ejemplo, true, null, null,
                new String[]{orderByAttrList},
                new String[]{orderByDirList}, false,
                false, null, null, null,
                null, null, null, null, null, false);
    }

    @Override
    public List<T> list(T ejemplo, String orderByAttrList,
            String orderByDirList, boolean like) {
        return this.list(ejemplo, true, null, null,
                new String[]{orderByAttrList},
                new String[]{orderByDirList}, like,
                false, null, null, null,
                null, null, null, null, null, false);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> list(T ejemplo, boolean all, Integer primerResultado,
            Integer cantResultados, String[] orderByAttrList,
            String[] orderByDirList, boolean like, boolean caseSensitive, String campoComparacion, List<Long> valoresComparacion, String tipoFiltro,
            List<String> atrMayIgual, List<Object> objMayIgual, List<String> atrMenIgual, List<Object> objMenIgual,
            String camposDistintos, boolean distintos) {

        JPASearchProcessor jpaSP = new JPASearchProcessor(
                HibernateMetadataUtil.getInstanceForSessionFactory(this
                        .getSessionFactory()));
        Search searchConfig = this.getSearchConfig(jpaSP, ejemplo, null, all,
                primerResultado, cantResultados, orderByAttrList,
                orderByDirList, like, caseSensitive, null, null, campoComparacion, valoresComparacion, tipoFiltro,
                atrMayIgual, objMayIgual, atrMenIgual, objMenIgual, camposDistintos, distintos);
        return jpaSP.search(em, searchConfig);
    }

    @Override
    public List<Map<String, Object>> listAtributos(T ejemplo, String[] atributos) {
        return this.listAtributos(ejemplo, atributos, true);
    }

    @Override
    public List<Map<String, Object>> listAtributos(T ejemplo,
            String[] atributos, boolean like) {
        return this.listAtributos(ejemplo, atributos, true, 0, null,
                null, null, like, true, null, null, null, null, null, null, null, null, null, null, false);
    }

    public List<Map<String, Object>> listAtributos(T ejemplo, String[] atributos,
            boolean all, Integer primerResultado, Integer cantResultados,
            String[] orderByAttrList, String[] orderByDirList, boolean like, boolean caseSensitive,
            List<String> atrMayIgual, List<Object> objMayIgual, List<String> atrMenIgual, List<Object> objMenIgual) {

        return this.listAtributos(ejemplo, atributos, all, primerResultado, cantResultados,
                orderByAttrList, orderByDirList, like, caseSensitive,
                null, null, null, null, null,
                atrMayIgual, objMayIgual, atrMenIgual, objMenIgual, null, false);

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> listAtributos(T ejemplo,
            String[] atributos, boolean all, Integer primerResultado,
            Integer cantResultados, String[] orderByAttrList,
            String[] orderByDirList, boolean like, boolean caseSensitive,
            String propiedadFiltroComunes, String comun, String campoComparacion, List<Long> valoresComparacion, String tipoFiltro,
            List<String> atrMayIgual, List<Object> objMayIgual, List<String> atrMenIgual, List<Object> objMenIgual,
            String camposDistintos, boolean distintos) {
        if (atributos == null || atributos.length == 0) {
            throw new RuntimeException(
                    "La lista de propiedades no puede ser nula o vacía");
        }

        JPASearchProcessor jpaSP = new JPASearchProcessor(
                HibernateMetadataUtil.getInstanceForSessionFactory(this
                        .getSessionFactory()));
        Search searchConfig = this.getSearchConfig(jpaSP, ejemplo, atributos,
                all, primerResultado, cantResultados, orderByAttrList,
                orderByDirList, like, caseSensitive,
                propiedadFiltroComunes, comun, campoComparacion, valoresComparacion, tipoFiltro,
                atrMayIgual, objMayIgual, atrMenIgual, objMenIgual, camposDistintos, distintos);
        return jpaSP.search(em, searchConfig);
    }

    private Search getSearchConfig(JPASearchProcessor jpaSP, T ejemplo,
            String[] atributos, boolean all, Integer primerResultado,
            Integer cantResultados, String[] orderByAttrList,
            String[] orderByDirList, boolean like, boolean caseSensitive,
            String propiedadFiltroComunes, String valorComun,
            String campoComparacion, List<Long> valoresComparacion, String tipoFiltro,
            List<String> atrMayIgual, List<Object> objMayIgual,
            List<String> atrMenIgual, List<Object> objMenIgual,
            String camposDistintos, boolean distintos) {

        Search searchConfig = new Search(this.getEntityBeanType());

        if (ejemplo != null) {
            ExampleOptions exampleOptions = new ExampleOptions();
            exampleOptions.setExcludeNulls(true);

            if (like) {
                exampleOptions.setLikeMode(ExampleOptions.ANYWHERE);
            }

            exampleOptions.setIgnoreCase(caseSensitive);

            searchConfig.addFilter(jpaSP.getFilterFromExample(ejemplo,
                    exampleOptions));
        }

        if (atrMayIgual != null && objMayIgual != null && atrMayIgual.size() == objMayIgual.size()) {
            int index = 0;
            for (String atr : atrMayIgual) {
                if (objMayIgual.get(index) != null) {
                    Filter[] filtros = new Filter[2];
                    filtros[0] = Filter.isNull(atr);
                    filtros[1] = Filter.greaterOrEqual(atr, objMayIgual.get(index));
                    searchConfig.addFilterOr(filtros);
                }
                index++;
            }
        }

        if (atrMenIgual != null && objMenIgual != null && atrMenIgual.size() == objMenIgual.size()) {
            int index = 0;

            for (String atr : atrMenIgual) {
                if (objMenIgual.get(index) != null) {
                    Filter[] filtros = new Filter[2];
                    filtros[0] = Filter.isNull(atr);
                    filtros[1] = Filter.lessOrEqual(atr, objMenIgual.get(index));
                    searchConfig.addFilterOr(filtros);
                }
                index++;
            }
        }

        if (valoresComparacion != null && campoComparacion != null && tipoFiltro != null) {
            int tam = valoresComparacion.size() / 1000;
            tam++;

            int inicio = 0;

            Filter[] filtros = new Filter[tam];
            for (int i = 0; i < tam; i++) {
                if (tipoFiltro.compareTo("OP_IN") == 0) {
                    filtros[i] = Filter.in(campoComparacion, valoresComparacion.subList(inicio, Math.min(valoresComparacion.size(), inicio + 1000)));
                } else {
                    filtros[i] = Filter.notIn(campoComparacion, valoresComparacion.subList(inicio, Math.min(valoresComparacion.size(), inicio + 1000)));
                }
                inicio += 1000;
            }
            if (tipoFiltro.compareTo("OP_IN") == 0) {
                searchConfig.addFilterOr(filtros);
            } else {
                searchConfig.addFilterAnd(filtros);
            }

        }

        if (valorComun != null && propiedadFiltroComunes != null) {

            String[] lista = propiedadFiltroComunes.split(",");
            String[] valores = valorComun.split(" ");

            for (int j = 0; j < valores.length; j++) {
                int index = 0;
                Filter[] filtros = new Filter[lista.length];

                for (int i = 0; i < lista.length; i++) {
                    Filter f = new Filter();
                    f.setProperty(lista[i]);
                    f.setValue("%" + valores[j] + "%");
                    f.setOperator(Filter.OP_ILIKE);
                    filtros[index] = f;
                    index++;
                }
                searchConfig.addFilterOr(filtros);
            }

        }

        if (!all && primerResultado != null && cantResultados != null) {
            searchConfig.setFirstResult(primerResultado);
            searchConfig.setMaxResults(cantResultados);
        }

        if (atributos != null && atributos.length > 0) {
            for (String a : atributos) {
                searchConfig.addField(a);
            }
            searchConfig.setResultMode(Search.RESULT_MAP);
        }

        if (distintos && camposDistintos != null) {
            String[] lista = camposDistintos.split(",");
            searchConfig.setDistinct(distintos);
            for (String atributo : lista) {
                searchConfig.addField(atributo);
            }

        } else {

            if (orderByAttrList != null && orderByDirList != null
                    && orderByAttrList.length == orderByDirList.length) {
                for (int i = 0; i < orderByAttrList.length; i++) {
                    if (orderByDirList[i].equalsIgnoreCase("desc")) {
                        searchConfig.addSortDesc(orderByAttrList[i]);
                    } else {
                        searchConfig.addSortAsc(orderByAttrList[i]);
                    }
                }
            } else if ((orderByAttrList != null && orderByDirList == null)
                    || (orderByAttrList == null && orderByDirList != null)) {
                throw new RuntimeException("No puede proporcionarse una lista de "
                        + "atributos para ordenamiento sin la correspondiente "
                        + "lista de direcciones de ordenamiento, o viceversa");
            } else if (orderByAttrList != null && orderByDirList != null
                    && orderByAttrList.length != orderByDirList.length) {
                throw new RuntimeException("No puede proporcionarse una lista de "
                        + "atributos para ordenamiento de tamaño dieferente a la "
                        + "lista de direcciones de ordenamiento");
            }

        }
        return searchConfig;
    }

//    private String getQuery(final List<CountAll> params) {
//
//        final StringBuffer queryString = new StringBuffer(
//                "SELECT count(*) from ");
//        queryString.append(getEntityBeanType().getSimpleName()).append(" o ");
//
//        boolean and = false;
//        for (CountAll rpm : params) {
//
//            if (rpm.getType().compareToIgnoreCase("Int") == 0
//                    && rpm.getValue() != null
//                    && rpm.getValue().compareToIgnoreCase("") != 0) {
//                if (!and) {
//                    and = true;
//                    queryString.append(" where ");
//                    queryString.append(" o.");
//                    queryString.append(rpm.getKey());
//                    queryString.append(" = ");
//                    queryString.append(rpm.getValue());
//                } else {
//                    queryString.append(" and o.");
//                    queryString.append(rpm.getKey());
//                    queryString.append(" = ");
//                    queryString.append(rpm.getValue());
//                }
//            } else if (rpm.getType().compareToIgnoreCase("String") == 0) {
//                if (!and) {
//                    and = true;
//                    queryString.append(" where ");
//                    queryString.append(" o.");
//                    queryString.append(rpm.getKey());
//                    queryString.append(" = '");
//                    queryString.append(rpm.getValue());
//                    queryString.append("'");
//                } else {
//                    queryString.append(" and o.");
//                    queryString.append(rpm.getKey());
//                    queryString.append(" = '");
//                    queryString.append(rpm.getValue());
//                    queryString.append("'");
//                }
//            }  else if (rpm.getType().compareToIgnoreCase("OP_IN") == 0) {
//                if (!and) {
//                    and = true;
//                    queryString.append(" where ");
//                    queryString.append(" o.");
//                    queryString.append(rpm.getKey());
//                    queryString.append(" IN (");
//                    
//                    ListIterator<Long> valueIterator = rpm.getIds().listIterator();
//                    
//                    while (valueIterator.hasNext()) {
//                        queryString.append(valueIterator.next().toString());
//                        if (valueIterator.hasNext()) {
//                            queryString.append(",");
//                        }
//                    }
//                    queryString.append(") ");
//                } else {
//                    queryString.append(" and ");
//                    queryString.append(" o.");
//                    queryString.append(rpm.getKey());
//                    queryString.append(" IN (");
//                   
//                    ListIterator<Long> valueIterator = rpm.getIds().listIterator();
//                    
//                    while (valueIterator.hasNext()) {
//                        queryString.append(valueIterator.next().toString());
//                        if (valueIterator.hasNext()) {
//                            queryString.append(",");
//                        }
//                    }
//                    
//                    queryString.append(") ");
//                }
//
//            } else if (rpm.getType().compareToIgnoreCase("NOT_IN") == 0) {
//
//                if (!and) {
//                    and = true;
//                    queryString.append(" where ");
//                    queryString.append(" o.");
//                    queryString.append(rpm.getKey());
//                    queryString.append(" NOT IN ( ");
//                    
//                    ListIterator<Long> valueIterator = rpm.getIds().listIterator();
//                    
//                    while (valueIterator.hasNext()) {
//                        queryString.append(valueIterator.next().toString());
//                        if (valueIterator.hasNext()) {
//                            queryString.append(",");
//                        }
//                    }
//                    
//                    queryString.append(") ");
//                } else {
//                    queryString.append(" and ");
//                    queryString.append(" o.");
//                    queryString.append(rpm.getKey());
//                    queryString.append(" NOT IN (");
//                    
//                    ListIterator<Long> valueIterator = rpm.getIds().listIterator();
//                    
//                    while (valueIterator.hasNext()) {
//                        queryString.append(valueIterator.next().toString());
//                        if (valueIterator.hasNext()) {
//                            queryString.append(",");
//                        }
//                    }
//                    queryString.append(") ");
//                }
//            }else if (rpm.getType().compareToIgnoreCase("DATES") == 0
//                    && rpm.getFechaDesde() != null
//                    && rpm.getFechaDesde().compareToIgnoreCase("") != 0) {
//                if (!and) {
//                    and = true;
//                    queryString.append(" where o.");
//                    queryString.append(rpm.getKey());
//                    queryString.append(" BETWEEN TO_DATE('");
//                    queryString.append(rpm.getFechaDesde());
//                    queryString.append("','dd/MM/yyyy HH24:MI') ");
//                    queryString.append(" and TO_DATE('");
//                    queryString.append(rpm.getFechaHasta());
//                    queryString.append("','dd/MM/yyyy HH24:MI') ");
//                } else {
//                    queryString.append(" and o.");
//                    queryString.append(rpm.getKey());
//                    queryString.append(" BETWEEN TO_DATE('");
//                    queryString.append(rpm.getFechaDesde());
//                    queryString.append("','dd/MM/yyyy HH24:MI') ");
//                    queryString.append(" and TO_DATE('");
//                    queryString.append(rpm.getFechaHasta());
//                    queryString.append("','dd/MM/yyyy HH24:MI') ");
//                }
//            }
//
//        }
//
//        return queryString.toString();
//
//    }
}
