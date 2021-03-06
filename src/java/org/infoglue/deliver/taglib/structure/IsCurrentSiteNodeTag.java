/* ===============================================================================
*
* Part of the InfoGlue Content Management Platform (www.infoglue.org)
*
* ===============================================================================
*
*  Copyright (C)
* 
* This program is free software; you can redistribute it and/or modify it under
* the terms of the GNU General Public License version 2, as published by the
* Free Software Foundation. See the file LICENSE.html for more information.
* 
* This program is distributed in the hope that it will be useful, but WITHOUT
* ANY WARRANTY, including the implied warranty of MERCHANTABILITY or FITNESS
* FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* 
* You should have received a copy of the GNU General Public License along with
* this program; if not, write to the Free Software Foundation, Inc. / 59 Temple
* Place, Suite 330 / Boston, MA 02111-1307 / USA.
*
* ===============================================================================
*/

package org.infoglue.deliver.taglib.structure;

import javax.servlet.jsp.JspException;

import org.infoglue.deliver.taglib.TemplateControllerTag;
import org.infoglue.deliver.util.RequestAnalyser;
import org.infoglue.deliver.util.Timer;

public class IsCurrentSiteNodeTag extends TemplateControllerTag {
	private static final long serialVersionUID = 4050206323348354355L;

	private Integer siteNodeId;
	
    public IsCurrentSiteNodeTag()
    {
        super();
    }

	public int doEndTag() throws JspException
    {
		Timer t = new Timer();
		setResultAttribute(new Boolean(this.getController().getIsCurrentSiteNode(siteNodeId)));
		RequestAnalyser.getRequestAnalyser().registerComponentStatistics("IsCurrentSiteNodeTag tag", t.getElapsedTimeNanos() / 1000);
		
		return EVAL_PAGE;
    }

    public void setSiteNodeId(String siteNodeId) throws JspException
    {
        this.siteNodeId = evaluateInteger("IsCurrentSiteNodeTag", "siteNodeId", siteNodeId);
    }
}
