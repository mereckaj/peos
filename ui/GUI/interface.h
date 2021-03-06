/*
 * DO NOT EDIT THIS FILE - it is generated by Glade.
 */
#ifndef _INTERFACE_H
#define _INTERFACE_H


#ifdef HAVE_CONFIG_H
#  include <config.h>
#endif

#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <string.h>

#include <gdk/gdkkeysyms.h>
#include <gtk/gtk.h>

#include "callbacks.h"
#include "support.h"
#include "tree.h"
#include "html.h"
#include "viewmenu.h"
#include "deletemenu.h"
#include "parse.h"
#include "glo.h"
#include "notebook.h"

GtkWidget* create_Peos (void);
GtkWidget* create_fileselection (void);
GtkWidget* create_exitdialog (void);
GtkWidget* create_errordialog (void);
GtkWidget* create_aboutdialog (void);
GtkWidget* create_inputdialog (gchar *string);
GtkWidget* create_deletedialog (gchar *string);
GtkWidget* create_rebinddialog (gchar *string);
void set_sensitive(void);
void draw_tree(int pid);
void draw_text(xmlNode *action);
void redisplay_menu(void);

#endif
