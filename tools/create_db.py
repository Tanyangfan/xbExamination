#!/usr/bin/env python
# _*_ coding:utf-8 _*_

import sqlite3
import os
from openpyxl import load_workbook

def create_db():
	db = sqlite3.connect('questions.db', isolation_level=None)
	
	# 建表
	sql = ('CREATE TABLE questions('
		'id INTEGER PRIMARY KEY,'
		'type BLOB,'
		'content BLOB,'
		'options BLOB,'
		'answer BLOB);')
	db.execute(sql)
	
	return db

def save_close_db(lst, db):
	sql = 'INSERT INTO questions(type,content,options,answer) VALUES(?,?,?,?);'
	db.execute('BEGIN')
	for q in lst:
		db.execute(sql, q.get_tuple())
	db.commit()
	db.execute('VACUUM')
	db.close()
	
class Question:
	__slots__ = (u'type', u'content', u'options', u'answer')

	def __init__(self, type, content, options, answer):
		self.type = type
		self.content = content
		self.options = options
		self.answer = answer

	def get_tuple(self):
		return (self.type,
				self.content,
				self.options,
				self.answer)

	def __str__(self):
		return u'type: %s\ncontent：%s\noptions：%s\nanswer：%s\n' % \
				(self.type, self.content, self.options, self.answer)
				
def load_question():
	#try:
	wb = load_workbook('questions.xlsx')
	
	# 获得当前正在显示的sheet, 也可以用wb.get_active_sheet()
	sheet = wb.active
	# 因为按行，所以返回A1, B1, C1这样的顺序
	lst = []
	for row in sheet.rows:
		cell = list(row)
		q = Question(cell[0].value, cell[1].value, cell[2].value, cell[3].value)
		print u'%s' % q
		lst.append(q)

	return lst
	#except Exception as e:
	#	print(e)
	
def main():
	# 删已有
	try:
		os.remove('questions.db')
	except Exception as e:
		print(e)

	# 诗
	lst = load_question()

	# 建数据库
	db = create_db()
	# 写数据库
	save_close_db(lst, db)

	# zip
	#with ZipFile('questions.db.zip', 'w') as myzip:
		#myzip.write('questions.db')

	#print('无内容的记录%d条' % empty_count)
	print(u'运行完毕')

main()

#def test():
#	load_question()
#
#test()
