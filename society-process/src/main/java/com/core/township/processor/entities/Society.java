package com.core.township.processor.entities;

import com.core.township.common.enums.TownSocietyStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Represents a 'society' (or housing complex/township) entity mapped to the 'society' table.
 * Implements Persistable to give explicit control over new-entity detection.
 */
@Table("society")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Society implements Persistable<String> {

  /** Primary Key, stored as BINARY(16) in the database. */
  @Id
  @Column("society_id")
  private String societyId;

  /** The official name of the society. */
  @Column("name")
  private String name;

  // --- Address Details ---

  @Column("country")
  private String country;

  @Column("state")
  private String state;

  @Column("city")
  private String city;

  @Column("address")
  private String address;

  @Column("pincode")
  private String pincode;

  /** An optional nearby landmark. */
  @Column("landmark")
  private String landmark;

  // --- Audit/Metadata ---

  /** The ID of the user who created this record. */
  @Column("created_by_user_id")
  private String createdByUserId;

  /**
   * The timestamp when the user account was created.
   * This is typically managed by the database or application logic upon creation.
   */
  @Column("created_at")
  @CreatedDate
  private LocalDateTime createdAt;

  /**
   * The timestamp when the user account was last updated.
   * This is updated whenever a change is made to the user's record.
   */
  @Column("updated_at")
  @LastModifiedDate
  private LocalDateTime updatedAt;

  /** The current status of the society (e.g., ACTIVE, PENDING). */
  @Column("status")
  private TownSocietyStatus status;

  @Override
  public String getId() {
    return societyId;
  }

  @Override
  @JsonIgnore
  @Transient
  public boolean isNew() {
    // If createdAt is null, it means the entity hasn't been persisted/audited yet.
    return societyId == null || createdAt == null;
  }
}
