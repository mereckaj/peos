#ifndef _PROCESS_TABLE_H
#define _PROCESS_TABLE_H
#include "action.h"

#define INST_ARRAY_INCR (256)    
#define PEOS_MAX_PID (10)

typedef enum {
    PEOS_NONE = 0x1,		/* Unoccupied */ 
    PEOS_READY = 0x2,		/* Just loaded, ready to run. */
    PEOS_RUNNING = 0x4,		/* VM has executed some instructions. */
    PEOS_DONE = 0x8,		/* Exited. */
    PEOS_ERROR = 0x10		/* Exited/aborted, w/error */
} process_status_t ;

typedef struct peos_context_tag {
    process_status_t status; 
    int pid;
    vm_context_t vm_context;
    int num_actions;
    char model[PATH_MAX]; 
    peos_action_t *actions;
} peos_context_t;

/* This has to be here because load_proc_table needs to set it. */
extern peos_context_t *current_process;

int peos_get_pid(peos_context_t *context);
peos_context_t *peos_get_context(int pid);
int peos_create_instance(char *model);
int peos_resume(int pid);
char **peos_list_instances();
peos_action_t **peos_list_actions(vm_act_state state);
peos_action_t **peos_find_actions(vm_act_state state, peos_action_t *actions, int num_actions);

#endif 