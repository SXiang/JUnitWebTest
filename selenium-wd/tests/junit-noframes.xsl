<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- import the default stylesheet -->
	<xsl:import
		href="jar:file:selenium-wd/lib/ant-junit-1.8.0.jar!/org/apache/tools/ant/taskdefs/optional/junit/xsl/junit-noframes.xsl" />

	<!-- override the template producing the test table header -->
	<xsl:template name="testcase.test.header">
		<xsl:param name="show.class" select="''" />
		<tr valign="top">
			<xsl:if test="boolean($show.class)">
				<th>Class</th>
			</xsl:if>
			<th width="20%">Name</th>
			<th>Status</th>
			<th width="30%">Type</th>
			<th nowrap="nowrap">Time(s)</th>

			<!-- ADDED -->
			<th>Screenshot</th>

		</tr>
	</xsl:template>
	<xsl:template match="failure">
		<xsl:call-template name="display-failures" />
	</xsl:template>
	<xsl:template match="error">
		<xsl:call-template name="display-failures" />
	</xsl:template>
	<!-- Style for the error and failure in the tescase template -->
	<xsl:template name="display-failures">
		<xsl:choose>
			<xsl:when test="not(@message)">
				N/A
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="@message" />
			</xsl:otherwise>
		</xsl:choose>
		<!-- display the stacktrace -->
		<code>
			<p />
			<!-- <xsl:call-template name="br-replace"> <xsl:with-param name="word" 
				select="."/> </xsl:call-template> -->
		</code>
		<!-- the later is better but might be problematic for non-21" monitors... -->
		<!-- pre><xsl:value-of select="."/></pre -->
	</xsl:template>
	<!-- override the template producing a test table row -->
	<xsl:template match="testcase" mode="print.test">
		<xsl:param name="show.class" select="''" />
		<xsl:if test="substring-before(@name,'[')">
			<tr valign="top">
				<xsl:attribute name="class">
        	<xsl:choose>
          		<xsl:when test="error">Error</xsl:when>
          		<xsl:when test="failure">Failure</xsl:when>
          		<xsl:otherwise>TableRowColor</xsl:otherwise>
       	 	</xsl:choose>
        </xsl:attribute>
				<xsl:variable name="class.href">
					<xsl:value-of
						select="concat(translate(../@package,'.','/'), '/', ../@id, '_', ../@name, '.html')" />
				</xsl:variable>
				<xsl:if test="boolean($show.class)">
					<td>
						<a href="{$class.href}">
							<xsl:value-of select="../@name" />
						</a>
					</td>
				</xsl:if>
				<td>
					<a name="{@name}" />
					<xsl:choose>
						<xsl:when test="boolean($show.class)">
							<a href="{concat($class.href, '#', @name)}">
								<xsl:value-of select="@name" />
							</a>
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="concat(substring(@name,1,20),'...')" />
						</xsl:otherwise>
					</xsl:choose>
				</td>
				<xsl:choose>
					<xsl:when test="failure">
						<td>Failure</td>
						<td>
							<xsl:apply-templates select="failure" />
						</td>
					</xsl:when>
					<xsl:when test="error">
						<td>Error</td>
						<td>
							<xsl:apply-templates select="error" />
						</td>
					</xsl:when>
					<xsl:otherwise>
						<td>Success</td>
						<td></td>
					</xsl:otherwise>
				</xsl:choose>
				<td>
					<xsl:call-template name="display-time">
						<xsl:with-param name="value" select="@time" />
					</xsl:call-template>
				</td>

				<!-- Added screenshot link for failed tests -->
				<td>
					<xsl:choose>
						<xsl:when test="failure">
							<a
								href="{concat('./screenshots/',substring-before(@name,'['),'.jpg')}">
								<xsl:value-of select="concat(substring(@name,1,6),'...')" />
							</a>
						</xsl:when>
						<xsl:when test="error">
							<a
								href="{concat('./screenshots/',substring-before(@name,'['),'.jpg')}">
								<xsl:value-of select="concat(substring(@name,1,6),'...')" />
							</a>
						</xsl:when>
					</xsl:choose>
				</td>

			</tr>
		</xsl:if>
	</xsl:template>

</xsl:stylesheet>