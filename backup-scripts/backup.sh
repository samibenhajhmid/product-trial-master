#!/bin/bash

# Dump de la base PostgreSQL
docker exec postgres13-container pg_dump -U user altentestdb > backup_$(date +%F).sql

# Upload sur Mega (avec megacmd installé)
mega-login ton_email ton_motdepasse
mega-put backup_$(date +%F).sql /BackupPostgres/
mega-logout
