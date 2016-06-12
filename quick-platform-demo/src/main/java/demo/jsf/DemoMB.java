package demo.jsf;

import com.quick.ext.primefaces.base.service.BaseEJB;
import com.quick.ext.primefaces.base.web.BaseMB;

import demo.ejb.StudentsSB;
import demo.entity.Students;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

//@ManagedBean
//@ViewScoped
@SessionScoped
@Named
//@AccessLogInterceptor
public class DemoMB extends BaseMB<Students, Students> implements Serializable{

    private String headerText;
    private boolean deleteRow;
    private boolean removeOnColumn;
    private boolean draggableColumns;
    private boolean sort;
    private boolean filter;
    private boolean resetColumnsAble;
    private boolean cellEdit;
    private boolean showIndex;
    private boolean resizableColumns;
    private boolean addOne;

    private boolean advanceColumnToggler;
    private boolean removeSelect;
    private boolean exportALl;
    private boolean exportPage;
    private boolean exportSelect;
    private String exportName = "export";
    private String exportSheetName = "sheet0";
    private String uiDatatableTable;
    private boolean globalFilter;
    private int freezeColumnsSize;
    @Inject
    private StudentsSB studentsSB;//= new StudentsSB();

    @Override
    protected BaseEJB<Students, Students> dao() {
        return studentsSB;
//        return new StudentsSB();
    }

    public void openSource() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("maximizable", true);
        options.put("minimizable", true);
        options.put("contentHeight", 500);
        options.put("contentWidth", 1024);
        RequestContext.getCurrentInstance().openDialog("demo_sources", options, null);
    }

    public void initTable() {
        setDataModel(null);
        setEntitys(null);
        getPageSelectEntitys().clear();
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public boolean isDeleteRow() {
        return deleteRow;
    }

    public void setDeleteRow(boolean deleteRow) {
        this.deleteRow = deleteRow;
    }

    public boolean isRemoveOnColumn() {
        return removeOnColumn;
    }

    public void setRemoveOnColumn(boolean removeOnColumn) {
        this.removeOnColumn = removeOnColumn;
    }

    public boolean isDraggableColumns() {
        return draggableColumns;
    }

    public void setDraggableColumns(boolean draggableColumns) {
        this.draggableColumns = draggableColumns;
    }

    public boolean isSort() {
        return sort;
    }

    public void setSort(boolean sort) {
        this.sort = sort;
    }

    public boolean isFilter() {
        return filter;
    }

    public void setFilter(boolean filter) {
        this.filter = filter;
    }

    public boolean isResetColumnsAble() {
        return resetColumnsAble;
    }

    public void setResetColumnsAble(boolean resetColumnsAble) {
        this.resetColumnsAble = resetColumnsAble;
    }

    public boolean isCellEdit() {
        return cellEdit;
    }

    public void setCellEdit(boolean cellEdit) {
        this.cellEdit = cellEdit;
    }

    public boolean isShowIndex() {
        return showIndex;
    }

    public void setShowIndex(boolean showIndex) {
        this.showIndex = showIndex;
    }

    public boolean isResizableColumns() {
        return resizableColumns;
    }

    public void setResizableColumns(boolean resizableColumns) {
        this.resizableColumns = resizableColumns;
    }

    public boolean isAddOne() {
        return addOne;
    }

    public void setAddOne(boolean addOne) {
        this.addOne = addOne;
    }

    public boolean isAdvanceColumnToggler() {
        return advanceColumnToggler;
    }

    public void setAdvanceColumnToggler(boolean advanceColumnToggler) {
        this.advanceColumnToggler = advanceColumnToggler;
    }

    public boolean isRemoveSelect() {
        return removeSelect;
    }

    public void setRemoveSelect(boolean removeSelect) {
        this.removeSelect = removeSelect;
    }

    public boolean isExportALl() {
        return exportALl;
    }

    public void setExportALl(boolean exportALl) {
        this.exportALl = exportALl;
    }

    public boolean isExportPage() {
        return exportPage;
    }

    public void setExportPage(boolean exportPage) {
        this.exportPage = exportPage;
    }

    public boolean isExportSelect() {
        return exportSelect;
    }

    public void setExportSelect(boolean exportSelect) {
        this.exportSelect = exportSelect;
    }

    public String getExportName() {
        return exportName;
    }

    public void setExportName(String exportName) {
        this.exportName = exportName;
    }

    public String getExportSheetName() {
        return exportSheetName;
    }

    public void setExportSheetName(String exportSheetName) {
        this.exportSheetName = exportSheetName;
    }

    public boolean isGlobalFilter() {
        return globalFilter;
    }

    public void setGlobalFilter(boolean globalFilter) {
        this.globalFilter = globalFilter;
    }

    public int getFreezeColumnsSize() {
        return freezeColumnsSize;
    }

    public void setFreezeColumnsSize(int freezeColumnsSize) {
        this.freezeColumnsSize = freezeColumnsSize;
    }

    public String getUiDatatableTable() {
        return uiDatatableTable;
    }

    public void setUiDatatableTable(String uiDatatableTable) {
        this.uiDatatableTable = uiDatatableTable;
    }

}
