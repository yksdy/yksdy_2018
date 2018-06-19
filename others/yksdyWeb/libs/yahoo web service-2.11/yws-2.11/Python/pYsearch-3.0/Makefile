#
# $Id: Makefile,v 1.12 2005/10/27 18:07:59 zwoop Exp $
#
# Simple Makefile to build packages, run tests, generate docs etc.
# Not very advanced, but it does the job for now ...
#


#
# Building, packaging and registration
#
.PHONY : build install dist register clean

build:
	python setup.py build

install:
	python setup.py install

dist:
	cp yahoo/search/ChangeLog ChangeLog
	python setup.py sdist --formats=gztar
	rm ChangeLog

register:
	python setup.py register

clean:
	rm -rf dist build


#
# Tests and docs
#
VPATH = yahoo/search:yahoo/search/dom
PYTHONPATH = ..

docs/yahoo.search.dom.%.html : dom/%.py yahoo/search/dom/__init__.py
	pydoc -w yahoo.search.dom.`basename $< .py`
	mv `basename $@` $@

docs/yahoo.search.%.html : %.py yahoo/search/__init__.py
	pydoc -w yahoo.search.`basename $< .py`
	mv `basename $@` $@

.PHONY : test docs

test:
	@cd test && gmake

pylint:
	@pylint --rcfile=pylint.rc yahoo/search/*.py yahoo/search/dom/*.py

docs:	docs/yahoo.search.html \
	docs/yahoo.search.web.html \
	docs/yahoo.search.news.html \
	docs/yahoo.search.image.html \
	docs/yahoo.search.video.html \
	docs/yahoo.search.local.html \
	docs/yahoo.search.term.html \
	docs/yahoo.search.myweb.html \
	docs/yahoo.search.audio.html \
	docs/yahoo.search.site.html \
	docs/yahoo.search.parser.html \
	docs/yahoo.search.factory.html \
	docs/yahoo.search.debug.html \
	docs/yahoo.search.version.html \
	docs/yahoo.search.webservices.html \
	docs/yahoo.search.dom.html \
	docs/yahoo.search.dom.web.html \
	docs/yahoo.search.dom.news.html \
	docs/yahoo.search.dom.image.html \
	docs/yahoo.search.dom.video.html \
	docs/yahoo.search.dom.local.html \
	docs/yahoo.search.dom.term.html \
	docs/yahoo.search.dom.myweb.html \
	docs/yahoo.search.dom.audio.html \
	docs/yahoo.search.dom.site.html \
	docs/index.html \
	docs/yahoo.html


docs/yahoo.search.html: yahoo/search/__init__.py
	pydoc -w yahoo.search
	mv `basename $@` $@

docs/yahoo.search.dom.html: yahoo/search/dom/__init__.py
	pydoc -w yahoo.search.dom
	mv `basename $@` $@

docs/yahoo.html: yahoo/__init__.py
	pydoc -w yahoo
	mv `basename $@` $@

docs/index.html: docs/yahoo.search.html
	cp $< $@

push:
	rsync -az -e ssh docs/ shell.sf.net:pysearch/htdocs/docs
