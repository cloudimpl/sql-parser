/* Copyright (C) 2011 Akiban Technologies Inc.
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

/* The original from which this derives bore the following: */

/*

   Derby - Class org.apache.derby.impl.sql.compile.GroupByColumn

   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to you under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

package com.akiban.sql.parser;

import com.akiban.sql.StandardException;

/**
 * A GroupByColumn is a column in the GROUP BY clause.
 *
 */
public class GroupByColumn extends OrderedColumn 
{
  private ValueNode columnExpression;

  /**
   * Initializer.
   *
   * @param colRef The ColumnReference for the grouping column
   */
  public void init(Object colRef) {
    this.columnExpression = (ValueNode)colRef;
  }

  /**
   * Fill this node with a deep copy of the given node.
   */
  public void copyFrom(QueryTreeNode node) throws StandardException {
    super.copyFrom(node);

    GroupByColumn other = (GroupByColumn)node;
    this.columnExpression = (ValueNode)getNodeFactory().copyNode(other.columnExpression,
                                                                 getParserContext());
  }

  /**
   * Prints the sub-nodes of this object.  See QueryTreeNode.java for
   * how tree printing is supposed to work.
   *
   * @param depth The depth of this node in the tree
   */

  public void printSubNodes(int depth) {
    super.printSubNodes(depth);

    if (columnExpression != null) {
      printLabel(depth, "columnExpression: ");
      columnExpression.treePrint(depth + 1);
    }
  }

  /**
   * Get the name of this column
   *
   * @return The name of this column
   */
  public String getColumnName() {
    return columnExpression.getColumnName();
  }

  public ValueNode getColumnExpression() {
    return columnExpression;
  }

  public void setColumnExpression(ValueNode cexpr) {
    this.columnExpression = cexpr;

  }

  /**
   * Accept the visitor for all visitable children of this node.
   *
   * @param v the visitor
   *
   * @exception StandardException on error
   */
  void acceptChildren(Visitor v) throws StandardException {

    super.acceptChildren(v);

    if (columnExpression != null) {
      columnExpression = (ValueNode)columnExpression.accept(v);
    }
  }

}
