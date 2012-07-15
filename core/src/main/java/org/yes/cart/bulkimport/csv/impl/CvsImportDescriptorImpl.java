/*
 * Copyright 2009 Igor Azarnyi, Denys Pavlov
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.yes.cart.bulkimport.csv.impl;

import org.yes.cart.bulkimport.csv.CsvImportColumn;
import org.yes.cart.bulkimport.csv.CsvImportFile;
import org.yes.cart.bulkimport.csv.CvsImportDescriptor;
import org.yes.cart.bulkimport.model.FieldTypeEnum;
import org.yes.cart.bulkimport.model.ImportColumn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * User: Igor Azarny iazarny@yahoo.com
 * Date: 11/27/11
 * Time: 12:06 PM
 */
public class CvsImportDescriptorImpl implements CvsImportDescriptor, Serializable {

    private CsvImportFile importFile;

    private Collection importColumns;

    private String importFolder;

    private String entityIntface;

    private String insertSql;


    /**
     * Get insert sql.
     *
     * @return insert sql
     */
    public String getInsertSql() {
        return insertSql;
    }

    /**
     * Set inser sql
     *
     * @param insertSql insert sql
     */
    public void setInsertSql(final String insertSql) {
        this.insertSql = insertSql;
    }


    /**
     * Default constructor.
     */
    public CvsImportDescriptorImpl() {
        importFile = new CsvImportFileImpl();
        importColumns = new ArrayList<CsvImportColumn>();
    }

    /**
     * Get full qualiffied entity interface. For example - org.yes.cart.domain.entity.Brand
     *
     * @return full qualiffied entity interface
     */
    public String getEntityIntface() {
        return entityIntface;
    }

    /**
     * Set full qualiffied entity interface.
     *
     * @param entityIntface entity interface
     */
    public void setEntityIntface(final String entityIntface) {
        this.entityIntface = entityIntface;
    }

    /**
     * Get the {@link ImportColumn} for object lookup.
     *
     * @return {@link ImportColumn} if found, otherwise null.
     */
    public ImportColumn getPrimaryKeyColumn() {
        if (importColumns != null) {
            for (Object columnObject : importColumns) {
                ImportColumn column = (ImportColumn) columnObject;
                if (column.getLookupQuery() != null && column.getFieldType() == FieldTypeEnum.FIELD) {
                    return column;
                }
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public CsvImportFile getImportFile() {
        return importFile;
    }

    /**
     * Set the {@link org.yes.cart.bulkimport.model.ImportFile}
     * for more detals.
     *
     * @param importFile import file decriptor.
     */
    protected void setImportFile(CsvImportFile importFile) {
        this.importFile = importFile;
    }

    /**
     * {@inheritDoc}
     */
    public Collection<ImportColumn> getImportColumns() {
        return importColumns;
    }

    /**
     * {@inheritDoc}
     */
    public Collection<ImportColumn> getImportColumns(FieldTypeEnum fieldType) {
        Collection<ImportColumn> columns = new ArrayList<ImportColumn>();
        for (ImportColumn importColumn : getImportColumns()) {
            if (importColumn.getFieldType() == fieldType) {
                columns.add(importColumn);
            }
        }
        return columns;
    }

    /**
     * Set the collection of import columns.
     *
     * @param importColumns collection of import columns to set.
     */
    protected void set(Collection<CsvImportColumn> importColumns) {
        this.importColumns = importColumns;
    }


    /**
     * {@inheritDoc}
     */
    public String getImportFolder() {
        return importFolder;
    }

    /**
     * Set the import folder.
     *
     * @param importFolder import folder to use.
     */
    public void setImportFolder(final String importFolder) {
        this.importFolder = importFolder;
    }


}